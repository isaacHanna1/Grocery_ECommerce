package com.watad.Dao;

import com.watad.model.User;
import com.watad.Dto.RegistrationDto;

public interface UserDao {
	
	void saveUser(User user);
	void saveUser(RegistrationDto registrationDto);

}
