package com.watad.Dao;

import com.watad.model.PasswordResetToken;

public interface PasswordResetTokenDao {
	
	
	void save(PasswordResetToken token);
	PasswordResetToken findByToken(String token);
	void delete(PasswordResetToken resetToken);

}
