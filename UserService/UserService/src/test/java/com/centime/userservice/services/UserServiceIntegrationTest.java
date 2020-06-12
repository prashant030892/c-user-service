package com.centime.userservice.services;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.ResourceAccessException;

import com.centime.userservice.dto.UserDto;
import com.centime.userservice.dto.UserTypeDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceIntegrationTest {

	@Autowired
	private UserService userService;

	@Test
	public void testGetUserTypes() throws JsonMappingException, JsonProcessingException {

		List<UserTypeDto> actualOutput = this.userService.getUserTypes();

		assertTrue(actualOutput.size() > 0);

	}

	@Test(expected = ResourceAccessException.class)
	public void testProcessDataInvalid() {
		this.userService.processData(new UserDto());
	}
}
