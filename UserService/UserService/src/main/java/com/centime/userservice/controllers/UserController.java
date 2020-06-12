package com.centime.userservice.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centime.userservice.dto.UserDto;
import com.centime.userservice.dto.UserTypeDto;
import com.centime.userservice.logging.LogMethodParam;
import com.centime.userservice.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/v1/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Operation(summary = "Check the application Status.")
	@ApiResponse(responseCode = "200", description = "Application is up", content = @Content)
	@GetMapping
	public ResponseEntity<String> checkStatus() {
		return new ResponseEntity<String>(userService.getStatus(), HttpStatus.OK);
	}

	@LogMethodParam
	@Operation(summary = "Greet the user passed. Prepend greeting and concatenate the user name and surname. The name parameter is mandatory.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "User greeted successfully", content = @Content),
			@ApiResponse(responseCode = "404", description = "Requested resource not found", content = @Content),
			@ApiResponse(responseCode = "400", description = "Invalid Request.", content = @Content) })
	@PostMapping
	public ResponseEntity<String> processData(@Valid @RequestBody UserDto userDto) {
		return new ResponseEntity<String>(userService.processData(userDto), HttpStatus.OK);
	}

	@Operation(summary = "Get the user types in hierarichal form. ")
	@GetMapping(value = "/types")
	public ResponseEntity<List<UserTypeDto>> getUserTypesWithParents() {

		return new ResponseEntity<List<UserTypeDto>>(userService.getUserTypes(), HttpStatus.OK);
	}

}
