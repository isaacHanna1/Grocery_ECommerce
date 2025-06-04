package com.watad.Dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.watad.model.Order;
import com.watad.model.OrderStatus;

@Repository
public class OrdersImp implements OrdersDao{

	
	@Autowired
	private SessionFactory mySessionFactory;
	
	public void setMySessionFactory(SessionFactory mySessionFactory) {
		this.mySessionFactory = mySessionFactory;
	}
	
	
	@Override
	public void insertNewOrder(Order order) {
		Session session = mySessionFactory.getCurrentSession();
		session.save(order);
	}


	@Override
	public void deleteOrder(long orderId) {
		Session session = mySessionFactory.getCurrentSession();
		Order order = session.get(Order.class,orderId);
		session.delete(order);
	}


	@Override
	public Order findOrderById(long orderId) {
		Session session = mySessionFactory.getCurrentSession();
		Order order = session.get(Order.class,orderId);
		return order;
	}


	@Override
	public List<Order> findAll() {
		
		Session session = mySessionFactory.getCurrentSession();
        String hql = "FROM Order ORDER BY  orderDate DESC";
        return session.createQuery(hql, Order.class).list();
        
        
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findInConditions(int pageNumber, Date from, Date to, OrderStatus orderStatus) {

		Session session = this.mySessionFactory.getCurrentSession();
		
		//Base Hql 
		StringBuilder  hql = new StringBuilder("FROM Order o WHere 1=1 ");
	    Map<String, Object> parameters = new HashMap<>();

		
	    if (from != null) {
	        hql.append("AND DATE(o.orderDate) >= :fromDate ");
	        parameters.put("fromDate", from);
	    }

	    if (to != null) {
	        hql.append("AND DATE(o.orderDate) <= :toDate ");
	        parameters.put("toDate", to);
	    }

	    if( orderStatus != null) {
	    	
	    	hql.append("AND o.status = :status");
	    	parameters.put("status", orderStatus);
	    	System.out.println("the status is :"+orderStatus);
	    }
		    Query query = session.createQuery(hql.toString());
		    // Set parameters
		    for (Map.Entry<String, Object> entry : parameters.entrySet()) {
		        query.setParameter(entry.getKey(), entry.getValue());
		    }
//		     Pagination
		    int pageSize = 10;
		    int firstResult = (pageNumber - 1) * pageSize;
		    query.setFirstResult(firstResult);
		    query.setMaxResults(pageSize);
		    
		    return query.getResultList();
	
	}


	@Override
	public void updateOrder(long orderId ,String statusComment , OrderStatus orderStatus) {
		
		Session session = this.mySessionFactory.getCurrentSession();
		Order order = session.get(Order.class,orderId);
		order.setStatus(orderStatus);
		order.setStatusComment(statusComment);
		session.merge(order);
	}


	@Override
	public long getAllOrderCount() {
		Session session = this.mySessionFactory.getCurrentSession();
		String hql = "SELECT COUNT(o) FROM Order o";
		org.hibernate.query.Query<Long> query = session.createQuery(hql, Long.class);
		long numberOfOrders = query.uniqueResult();
		
		return numberOfOrders;
	}



}
