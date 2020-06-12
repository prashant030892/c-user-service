package com.centime.userservice.exception;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * This class works as global exception handler for the application. Any
 * uncaught exception is handled in this class and appropriate response is
 * returned to the client.
 * 
 * @author prkala
 *
 */
@RestControllerAdvice
@Slf4j
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * This method handles the exception when the json passed to the controller
	 * method is invalid(fails validation constraints put on the DTO).
	 */

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		log.error("Arguments not valid", ex);

		Map<String, Object> body = createBody(status);

		// Get all validation errors
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		body.put("validationErrors", errors);

		return new ResponseEntity<>(body, headers, status);

	}

	/**
	 * This method handles the exception where the json structure of the request is
	 * incorrect.
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error("Json passed is not correct", ex);
		return createErrorObject("Invalid Json passed. Please check the request body.", status);
	}

	/**
	 * This method handles the connection exception which occurs if any of the
	 * services is down.
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler
	public ResponseEntity<Object> handleConnectionException(ResourceAccessException ex) {
		log.error("Resource not found", ex);
		return createErrorObject(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	/**
	 * This universal handler catches any unhandled exception in the application.
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler
	public ResponseEntity<Object> universalHandler(Exception ex) {
		log.error("Exception occurred",ex);
		return createErrorObject("Unknown error ocurred. Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * This is a common utility method which is called by multiple handlers to
	 * create the error object.
	 * 
	 * @param message
	 * @param status
	 * @return
	 */
	private ResponseEntity<Object> createErrorObject(String message, HttpStatus status) {

		Map<String, Object> body = createBody(status);

		body.put("errorMessage", message);

		return new ResponseEntity<>(body, status);
	}

	/**
	 * This method is called to create the response body for the error response.
	 * 
	 * @param status
	 * @return
	 */
	private Map<String, Object> createBody(HttpStatus status) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", status.value());
		return body;
	}

}
