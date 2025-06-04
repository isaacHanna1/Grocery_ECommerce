package com.watad.resetPassword;

import com.watad.model.User;

public abstract class  PaswordResetTemplate {
	
	
	public final void resetPassword(String userIdentifier) {
		User user = findUser(userIdentifier);
		if(user == null) {
			handleUserNotFound();
			return ; 
		}
		String token = createToken();
		saveToken(user, token);
		String resultUrl = generateResetUrl(token);
		sendResetLink(userIdentifier, resultUrl);
	}
	
	abstract User findUser(String userIdentifier);
	abstract void handleUserNotFound();
	abstract String createToken();
	abstract void saveToken(User user , String token );
	abstract String generateResetUrl(String token);
	abstract void sendResetLink(String userIdentifier , String resultUrl);

}
