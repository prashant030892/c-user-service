package com.centime.userservice.enums;

import lombok.Getter;

@Getter
public enum ServiceStatus {
	UP("Up"), DOWN("Down");

	private String status;

	ServiceStatus(String status) {
		this.status = status;
	}

}
