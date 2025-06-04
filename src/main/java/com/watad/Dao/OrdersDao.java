package com.watad.Dao;

import java.util.Date;
import java.util.List;

import com.watad.model.Order;
import com.watad.model.OrderStatus;

public interface OrdersDao {
	
	
	
	public void insertNewOrder(Order order);
	Order findOrderById(long orderId);
	void deleteOrder(long orderId);
	List<Order> findAll();
	List <Order> findInConditions(int pageNumber, Date from , Date to , OrderStatus orderStatus);
	void updateOrder(long orderId ,String statusComment , OrderStatus orderStatus);
	long getAllOrderCount();
}
