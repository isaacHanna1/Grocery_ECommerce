package com.watad.Dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.watad.model.Order;
import com.watad.model.OrderItems;

@Repository
public class OrderItemDaoImp implements OrderItemDao{

	
	@Autowired
	private SessionFactory mySessionFactory;
	
	public void setMySessionFactory(SessionFactory mySessionFactory) {
		this.mySessionFactory = mySessionFactory;
	}
	
	
	@Override
	public void addOrderItems(List<OrderItems> orderItems , long orderId) {
		Session session = mySessionFactory.getCurrentSession();
		for(OrderItems orderItem : orderItems) {
			Order order = new Order();
			order.setId(orderId);
			orderItem.setOrder(order);
			session.saveOrUpdate(orderItem);
		}
	}


	@Override
	public Order getOrderDetails(long orderId) {
		Session session = mySessionFactory.getCurrentSession();
		Order order = session.get(Order.class,orderId);
		return order;
	}

	@Override
	public OrderItems getOrderItems(long orderId) {
		Session session = mySessionFactory.getCurrentSession();
		OrderItems orderItems = session.get(OrderItems.class,orderId);
		return orderItems;
	}

	
}
