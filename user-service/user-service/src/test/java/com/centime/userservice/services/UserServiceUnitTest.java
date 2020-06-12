package com.centime.userservice.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.mockito.Mockito.*;
import com.centime.userservice.dto.UserDto;
import com.centime.userservice.enums.ServiceStatus;
import com.centime.userservice.services.impl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application.properties")
public class UserServiceUnitTest {

	@Mock
	private HealthCheckService healthCheckService;

	@InjectMocks
	private UserServiceImpl userService;

	private UserDto userDto;

	@Before
	public void setup() {
		userDto = new UserDto();
		userDto.setName("John");
		userDto.setSurname("Doe");
		when(healthCheckService.getStatus()).thenReturn(ServiceStatus.UP.getStatus());
	}

	@Test
	public void testGetStatus() {
		assertEquals(userService.getStatus(), "Up");

	}

//	@Test
//	public void testProcessData() {
//		String result = userService.processData(userDto);
//		assertEquals("Hello John Doe", result);
//	}

}
