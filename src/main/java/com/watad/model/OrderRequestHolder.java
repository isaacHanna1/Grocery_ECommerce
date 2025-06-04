package com.watad.model;

import java.util.List;

public class OrderRequestHolder {

	
	private Order order;
	private List<OrderItems> orderItems;
	
	
	public OrderRequestHolder() {
	}
	public OrderRequestHolder(Order order, List<OrderItems> orderItems) {
		this.order = order;
		this.orderItems = orderItems;
	}
	public Order getOrder() {
		return order;
	}
	public List<OrderItems> getOrderItems() {
		return orderItems;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}
	
	
	
	
}
