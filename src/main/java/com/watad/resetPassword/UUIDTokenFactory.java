package com.watad.resetPassword;

import java.util.UUID;

public class UUIDTokenFactory implements TokenFactory {

	@Override
	public String createToken() {
		System.out.println(UUID.randomUUID().toString());
		return UUID.randomUUID().toString();
	}

}
