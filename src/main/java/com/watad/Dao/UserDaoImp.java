package com.watad.Dao;

import java.util.Arrays;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
import com.watad.Dto.RegistrationDto;
import com.watad.model.Role;
import com.watad.model.User;

@Component
public class UserDaoImp implements UserDao{

	@Autowired
	private SessionFactory mySessionFactory;
	
	public void setMySessionFactory(SessionFactory mySessionFactory) {
		this.mySessionFactory = mySessionFactory;
	}
	@Autowired
	private RoleDao roleDao;
	
	@Override
	public void saveUser(User user) {
		Session session = this.mySessionFactory.getCurrentSession();
		System.out.println("called  dao");
		session.save(user);
	}


	@Override
	@Transactional
	public void saveUser(RegistrationDto registrationDto) {
		User user = new User();
		user.setUserName(registrationDto.getUserName());
		System.out.println("city : "+registrationDto.getUserName());

		user.setGovernment(registrationDto.getGovernment());
		user.setCity(registrationDto.getCity());
		user.setUserPhone(registrationDto.getPhone());
		user.setUserEmail(registrationDto.getEmail());
		user.setPassword(registrationDto.getPassword());
		user.setUserAddress(registrationDto.getAddress());
		Role role = roleDao.findByName("USER");
		user.setRoles(Arrays.asList(role));
		System.out.println("city : "+registrationDto.getCity());
		saveUser(user);
	}

}
