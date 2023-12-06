package com.watad.Dao;

import com.watad.Dto.verificationTokenDto;
import com.watad.model.User;
import com.watad.model.VerificationToken;

public interface VerificationTokenDao {

	void createVerificationToken(verificationTokenDto token);
	User getUser(String token);
	String getVerificationToken(long userID);
	VerificationToken getVerificationTokenBytoken (String token);
}
