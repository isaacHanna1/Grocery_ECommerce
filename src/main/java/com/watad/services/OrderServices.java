package com.watad.services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watad.Dao.OrderItemDao;
import com.watad.Dao.OrdersDao;
import com.watad.model.Order;
import com.watad.model.OrderItems;
import com.watad.model.OrderStatus;

@Service
public class OrderServices {

	@Autowired
	private OrderItemDao orderItemDao;
	
	@Autowired
	private OrdersDao ordersDao;
	
	
	@Transactional
	public void makeOrder(Order order , List<OrderItems> orderItems) {
		
		System.out.println("the order id is "+order.getUser().getId());
		ordersDao.insertNewOrder(order);
		long orderId = order.getId();
		
		orderItemDao.addOrderItems(orderItems,orderId);
		
	}
	
	@Transactional
	public List<Order> findAllOrders(){
			return ordersDao.findAll();
	}

	@Transactional
	public List<Order> findWhere(int pageNumber , Date from , Date to ,OrderStatus orderStatus){
			return ordersDao.findInConditions(pageNumber, from, to, orderStatus);
	}
	@Transactional
	public void deleteOrder(long orderId) {
		ordersDao.deleteOrder(orderId);
	}
	
	@Transactional
	public void updateOrderStatusAndComment(long orderId , String orderComment , OrderStatus orderStatus) {
		ordersDao.updateOrder(orderId, orderComment, orderStatus);
	}
	

	@Transactional
	public long getOrdercount() {
		return ordersDao.getAllOrderCount();
	}
	
}
