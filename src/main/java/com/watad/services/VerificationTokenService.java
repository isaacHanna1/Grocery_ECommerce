package com.watad.services;

import java.time.LocalDateTime;

public interface VerificationTokenService {
	
	String generateToken();
	LocalDateTime expireDate();
	boolean isExpired(LocalDateTime expireDate);

}
