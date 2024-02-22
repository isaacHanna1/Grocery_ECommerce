package com.watad.services;


import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class VerificationTokenServiceImp implements VerificationTokenService {

	@Override
	public String generateToken() {
	    return UUID.randomUUID().toString();
	}
	


	

}
