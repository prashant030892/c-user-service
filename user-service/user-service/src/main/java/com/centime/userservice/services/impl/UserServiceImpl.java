package com.centime.userservice.services.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.reactive.function.client.WebClient;

import com.centime.userservice.domain.UserType;
import com.centime.userservice.dto.UserDto;
import com.centime.userservice.dto.UserTypeDto;
import com.centime.userservice.repositories.UserTypeRepository;
import com.centime.userservice.services.HealthCheckService;
import com.centime.userservice.services.UserService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * This class is called upon by the UserControler to handle the business logic
 * and/or call services/persistence layer.
 * 
 * @author prkala
 *
 */
@Component
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private WebClient webClient;

	@Value("${greeting.service.url}")
	String greetingServiceUrl;

	@Value("${formatting.service.url}")
	String formattingServiceUrl;

	@Autowired
	private HealthCheckService healthCheckService;

	@Autowired
	private UserTypeRepository userTypeRepository;

	/**
	 * This method calls the HealthService getStatus method to get the health
	 * status.
	 */
	@Override
	public String getStatus() {
		log.debug("Getting health status from the healthCheck service");
		return healthCheckService.getStatus();
	}

	/**
	 * This service method calls two other external microservices. First call is
	 * made to the Greeting Service to get the greeting. Second web service call is
	 * the post call made to Formatting service by passing the Json received in this
	 * method. Finally both the responses are concatenated and sent to the user.
	 */
	@Override
	public String processData(UserDto userDto) {

		String greeting = null;
		String formattedName = null;

		try {
			log.debug("Calling greeting service");
			greeting = this.webClient.get().uri(greetingServiceUrl).retrieve().bodyToMono(String.class).block();
			log.debug("Greeting service called successfully");
			formattedName = this.webClient.post().uri(formattingServiceUrl).body(Mono.just(userDto), UserDto.class)
					.retrieve().bodyToMono(String.class).block();
			log.debug("Formatting service invoked successfully.");

		} catch (Exception e) {
			log.error("Error occurred during calling the services: " + e.getMessage());
			throw new ResourceAccessException("Resource not found.");
		}

		return greeting + " " + formattedName;
	}

	/**
	 * This public API method is use to get the userTypes along with there
	 * sub-classes. It calls private createUserTypeDto mehtod to generate
	 * UserTypeDto objects for each UserType object.
	 */
	@Override
	public List<UserTypeDto> getUserTypes() {
		List<UserType> parentUserTypes = this.userTypeRepository.findAll().stream()
				.filter(userType -> userType.getParent() == null).collect(Collectors.toList());

		List<UserTypeDto> userTypeDtos = new LinkedList<>();

		for (UserType parentUserType : parentUserTypes) {
			UserTypeDto userTypeDto = createUserTypeDto(parentUserType);
			userTypeDtos.add(userTypeDto);
		}
		return userTypeDtos;
	}

	/**
	 * This private method uses recursion to create UserTypeDto from the userType
	 * object passed. All the children are also passed recursively to get the
	 * UserTypeDto for each.
	 * 
	 * @param userType
	 * @return
	 */
	private UserTypeDto createUserTypeDto(UserType userType) {
		UserTypeDto userTypeDto = new UserTypeDto(userType.getName());

		for (UserType childUserType : userType.getChildren()) {
			UserTypeDto childUserTypeDto = createUserTypeDto(childUserType);
			userTypeDto.getSubclasses().add(childUserTypeDto);
		}
		return userTypeDto;
	}

}
