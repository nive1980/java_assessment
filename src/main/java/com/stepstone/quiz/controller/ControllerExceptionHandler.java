package com.stepstone.quiz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;

/**
 * @@author Nivedita Singh
 * This class handles all the esceptions gthrown by the application and returns different statuses
 */
@ControllerAdvice(annotations = { RestController.class})
public class ControllerExceptionHandler
{
	private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiErrorResponse> handleException(Exception e) {
		logger.error("error  is {}",e.getMessage());
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiErrorResponse);
	}


	@Data
	static class ApiErrorResponse {
		private HttpStatus status;

		private String message;

		ApiErrorResponse(HttpStatus status) {
			this.status = status;
		}
		ApiErrorResponse(HttpStatus status,String message) {
			this.status = status;
			this.message = message;
		}
	}
}