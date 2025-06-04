package com.watad.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthUtils {

	
	public static boolean isUserIsLoggedIn() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth != null && auth.isAuthenticated() && getLoggedInUserName() != null;
	}
	
	public static String getLoggedInUserName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null && auth.getPrincipal() instanceof UserDetails) {
			return ((UserDetails)auth.getPrincipal()).getUsername();
		}else {
			return null;
		}

	}
	
}
