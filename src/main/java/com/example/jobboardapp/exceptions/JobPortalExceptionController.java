package com.example.jobboardapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JobPortalExceptionController {

	@ExceptionHandler(value = InvalidJobSeekerException.class)
	public ResponseEntity<Object> handleException(InvalidJobSeekerException exception) {
		return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = InvalidJobApplicationException.class)
	public ResponseEntity<Object> handleException(InvalidJobApplicationException exception) {
		return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = InvalidJobException.class)
	public ResponseEntity<Object> handleException(InvalidJobException exception) {
		return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = InvalidRecruiterException.class)
	public ResponseEntity<Object> handleException(InvalidRecruiterException exception) {
		return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(value = JobPortalValidationException.class)
	public ResponseEntity<Object> handleException(JobPortalValidationException exception) {
		return new ResponseEntity<>(exception.getMessages(), HttpStatus.BAD_REQUEST);
	}
}
