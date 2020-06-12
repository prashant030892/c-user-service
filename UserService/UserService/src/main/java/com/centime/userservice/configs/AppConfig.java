package com.centime.userservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import brave.sampler.Sampler;

/**
 * This configuration class is used to create beans which then can be injected
 * in the entire application.
 * 
 * @author prkala
 *
 */
@Configuration
public class AppConfig {

	/**
	 * Sampler used in tracing logs. All the logs are eligible for sampling.
	 * 
	 * @return
	 */
	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}

	/**
	 * WebClient is a replacement for RestTemplate as RestTemplate is going to be
	 * deprecated.
	 * 
	 * @return
	 */
	@Bean
	public WebClient webClient() {
		return WebClient.create();
	}
}
