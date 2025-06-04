package com.watad.Dao;

import java.util.List;

import com.watad.model.Order;
import com.watad.model.OrderItems;

public interface OrderItemDao {
	
	void addOrderItems(List<OrderItems> orderItems , long orderId);
    Order getOrderDetails(long orderId);
	OrderItems getOrderItems(long orderId);
}
