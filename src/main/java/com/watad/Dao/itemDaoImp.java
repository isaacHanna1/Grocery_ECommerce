package com.watad.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.watad.model.Item;


@Component
public class itemDaoImp implements itemDao {


	@Autowired
	private SessionFactory mySessionFactory;
	
	public void setMySessionFactory(SessionFactory mySessionFactory) {
		this.mySessionFactory = mySessionFactory;
	}
	
	
	@Override
	public Item addingNewItem(Item item) {

		Session session = this.mySessionFactory.getCurrentSession();
		session.save(item);
		return item;
		
	}

}
