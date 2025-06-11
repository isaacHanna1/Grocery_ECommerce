package com.watad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.watad.Dao.UserDao;
import com.watad.customException.InactiveUserException;
import com.watad.model.User;

@Service
public class CustomUserDetailsService 
		implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userDao.findByPhone(username);

		if(user == null) {
			System.out.println("user not found exception from cutom user details");
            throw new UsernameNotFoundException("لا يوجد مستخدم: " + username);
		}
		if(!user.isActive()) {
			System.out.println("not active");
            throw new InactiveUserException("قم بتفعيل الحساب" + username);
		}else {
			UserDetails userDetails = org.springframework.security.core.userdetails.User
					.withUsername(user.getUserPhone())
					.password(user.getPassword())
					.roles(user.getRoles().get(0).getRoleName())
					.build();
			return userDetails;
		}
	}

	
//	public UserDetails loadUserByUsername (String phoneNumber) throws UsernameNotFoundException{
//
//			User user = userDao.findByPhone(phoneNumber);
//			if(user == null) {
//				System.out.println("user not found exception from cutom user details");
//	            throw new UsernameNotFoundException("لا يوجد مستخدم: " + phoneNumber);
//
//			}
//			else{UserDetails userDetails = org.springframework.security.core.userdetails.User
//					.withUsername(user.getUserPhone())
//					.password(user.getPassword())
//					.roles(user.getRoles().get(0).getRoleName())
//					.build();
//			return userDetails;
//			}
//	}

	}
	
	
