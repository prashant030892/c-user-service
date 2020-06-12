package com.centime.userservice.dto;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserTypeDto {
	@JsonProperty(value = "Name")
	private String name;
	@JsonProperty(value = "Sub Classes")
	private List<UserTypeDto> subclasses = new LinkedList<UserTypeDto>();

	public UserTypeDto() {
	}

	public UserTypeDto(String name) {
		this.name = name;
	}

}
