package com.watad.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.watad.Dao.OrderItemDao;
import com.watad.model.Order;
import com.watad.model.OrderItems;

@Service
public class OrderItemsServices {

	@Autowired
	private OrderItemDao orderItemDao;
	
	@Transactional
	public Order getOrderDetails(long orderId) {
		Order order =  orderItemDao.getOrderDetails(orderId);
		return order;
	}
	
	@Transactional
	public OrderItems getOrderItems(long orderId) {
		OrderItems orderItems =  orderItemDao.getOrderItems(orderId);
		return orderItems;
	}
	
	public String getOrderInFormat(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	    String formattedDate = date != null ? dateFormat.format(date) : null;
	    return formattedDate;
	}
	
	@Transactional
	public double getOrderItemsTotal(long orderId) {
		Order order = getOrderDetails(orderId);
		double total = 0 ;
		for(OrderItems oI: order.getOrderItems()) {
			double price = oI.getPrice()*oI.getQuantity();
			total = total + price;
		}
		return total;
	}
	
	@Transactional
	public List<OrderItems> getAllOrderItems(long orderId){
		orderItemDao.getOrderDetails(orderId).getOrderItems().size();
		return orderItemDao.getOrderDetails(orderId).getOrderItems();
	}
	
} 
