package com.watad.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class VerificationTokenServiceImp implements VerificationTokenService {

	private final int  expireDateDuration = 3; //in hours
	@Override
	public String generateToken() {
	    return UUID.randomUUID().toString();
	}
	
	@Override
	public LocalDateTime expireDate() {
        return LocalDateTime.now().plus(expireDateDuration, ChronoUnit.HOURS);
	}
	
	@Override
	public boolean isExpired(LocalDateTime expireDate) {

		// return true if expired
		LocalDateTime now =  LocalDateTime.now();
		if (now.isAfter(expireDate)) {
			return true;
		}
		return false;
	}

	

}
