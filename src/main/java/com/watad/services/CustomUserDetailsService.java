package com.watad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.watad.Dao.UserDao;
import com.watad.model.User;

@Service
public class CustomUserDetailsService 
		implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userDao.findByEmail(username);
		
		if(user == null) {
            throw new UsernameNotFoundException("لا يوجد مستخدم: " + username);
		}
		UserDetails userDetails = org.springframework.security.core.userdetails.User
				.withUsername(user.getUserEmail())
				.password(user.getPassword())
				.roles(user.getRoles().get(0).getRoleName())
				.build();
		return userDetails;
		
	}

	
	
}
