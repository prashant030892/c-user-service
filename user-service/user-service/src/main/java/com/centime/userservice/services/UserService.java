package com.centime.userservice.services;

import java.util.List;

import com.centime.userservice.dto.UserDto;
import com.centime.userservice.dto.UserTypeDto;

public interface UserService {
	String getStatus();

	String processData(UserDto userDto);

	List<UserTypeDto> getUserTypes();

}
