package com.example.jobboardapp.exceptions;

public class InvalidCompanyException extends RuntimeException{

    public InvalidCompanyException() {
    }

    public InvalidCompanyException(String message) {
        super(message);
    }
}
