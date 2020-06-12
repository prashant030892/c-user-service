package com.centime.userservice.services.impl;

import org.springframework.stereotype.Service;

import com.centime.userservice.enums.ServiceStatus;
import com.centime.userservice.services.HealthCheckService;

/**
 * This service is used to check the health status of the application.
 * 
 * @author prkala
 *
 */
@Service
public class HealthCheckServiceImpl implements HealthCheckService {

	@Override
	public String getStatus() {
		// For now simply return status as up. Later on it can be further improved to
		// check the health status of the services which are being refrenced by the User
		// service.
		return ServiceStatus.UP.getStatus();
	}

}
