package com.example.jobboardapp.exceptions;

public class InvalidJobSeekerException extends RuntimeException {

	public InvalidJobSeekerException() {
		super();
	}

	public InvalidJobSeekerException(String msg) {
		super(msg);
	}
}
