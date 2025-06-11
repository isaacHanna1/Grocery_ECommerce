package com.watad.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

public class Utils {

	
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
	public static String getDomain(HttpServletRequest request) {
		try {
			URI uri = new URI(request.getRequestURL().toString());
			String domainWithPort = uri.getHost() + ":" + uri.getPort();
			return (domainWithPort.startsWith("www.")) ? domainWithPort.substring(4) : domainWithPort;
		} catch (URISyntaxException e) {
			return null;
		}
	}

	public static String encodeURL(String url) {
		try {
			// Encode the URL using UTF-8 encoding
			return URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// Handle encoding exception
			e.printStackTrace();
			return null;
		}
	}

}
