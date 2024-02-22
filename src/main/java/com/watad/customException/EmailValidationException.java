package com.watad.customException;

public class EmailValidationException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmailValidationException(String message) {
        super(message);
    }
}
