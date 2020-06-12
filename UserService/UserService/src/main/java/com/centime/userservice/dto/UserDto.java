package com.centime.userservice.dto;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {

	@JsonProperty(value = "Name")
	@NotEmpty
	//Validate that the name passed should not be empty.
	private String name;

	@JsonProperty(value = "Surname")
	private String surname;

}
