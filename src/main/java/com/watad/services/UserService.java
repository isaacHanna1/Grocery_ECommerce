package com.watad.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watad.Dao.UserDao;
import com.watad.model.User;

@Service
public class UserService {

	
	@Autowired
	private UserDao userDao;
	
	@Transactional
	public void changeUserAddress(long userId,String goverment , String city , String userAdress) {
		userDao.updateUserAddress(userId, goverment, city, userAdress);
	}
	
	@Transactional
	public String getUserRole(long id) {
		User user = userDao.findById(id); 
		if (user == null )return "USER"; // this normal user
		
		return user.getRoles().get(0).getRoleName();
	}
	
	@Transactional
	public String getEmailByPhone(String phone) {
		return userDao.findEmailByPhone(phone);
	}
	
	
}
