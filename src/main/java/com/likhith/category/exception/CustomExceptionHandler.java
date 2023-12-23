package com.likhith.category.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.likhith.category.dto.CategoryResponse;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(TechnicalException.class)
	public ResponseEntity<CategoryResponse> technicalException(TechnicalException exception) {
		ErrorMessage errorMessage = new ErrorMessage(exception.getStatusCode(), exception.getErrorMessage());
		return ResponseEntity.status(errorMessage.getStatusCode()).body(new CategoryResponse(errorMessage));
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<CategoryResponse> validationException(ValidationException exception) {
		ErrorMessage errorMessage = new ErrorMessage(exception.getStatusCode(), exception.getErrorMessage());
		return ResponseEntity.status(errorMessage.getStatusCode()).body(new CategoryResponse(errorMessage));
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<CategoryResponse> runtimeException(RuntimeException exception) {
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Unexpected Error occured. Please try again after sometime");
		return ResponseEntity.status(errorMessage.getStatusCode()).body(new CategoryResponse(errorMessage));
	}
}