package com.centime.userservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * Open API- Swagger configuration class.
 * 
 * @author prkala
 *
 */
@Configuration
public class OpenAPIConfig {

	@Bean
	public OpenAPI docket() {
		return new OpenAPI().components(new Components())
				.info(new Info().title("User-Service").description("All the user related services"));
	}
}
