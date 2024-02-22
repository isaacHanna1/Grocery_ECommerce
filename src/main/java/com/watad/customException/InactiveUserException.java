package com.watad.customException;
import org.springframework.security.core.AuthenticationException;

public class InactiveUserException extends AuthenticationException {

	
	
	public InactiveUserException(String msg, Throwable t) {
		super(msg, t);
	}

    public InactiveUserException(String msg) {
        super(msg);
    }

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
