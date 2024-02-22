package com.watad.Dao;

import com.watad.model.User;

import javax.servlet.http.HttpServletRequest;

import com.watad.Dto.RegistrationDto;

public interface UserDao {
	
	void saveUser(User user);
	void saveUser(RegistrationDto registrationDto , HttpServletRequest req);
	User findByEmail(String email);
	User findByPhone(String phone);
	User findById(long id);
	void activeUserAccount(long id );
	
}
