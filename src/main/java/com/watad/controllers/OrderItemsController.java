package com.watad.controllers;


import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.watad.model.Order;
import com.watad.model.OrderItems;
import com.watad.model.OrderStatus;
import com.watad.services.OrderItemsServices;
import com.watad.services.OrderServices;

@RestController
public class OrderItemsController {

	@Autowired
	private OrderItemsServices orderItemsServices;
	
	
	@Autowired
	private OrderServices orderServices;
	
	@GetMapping("/orderDetails")
	public ModelAndView orderDetails(@RequestParam long orderId) {
		
	    ModelAndView modelAndView = new ModelAndView();
		Order order = orderItemsServices.getOrderDetails(orderId);
		modelAndView.addObject("order", order);
		String formatedDate = orderItemsServices.getOrderInFormat(order.getOrderDate());
		modelAndView.addObject("orderDateFormat",formatedDate);
		double total = orderItemsServices.getOrderItemsTotal(orderId);
		modelAndView.addObject("totalPrice", total);
		
		List<OrderItems> allItems = orderItemsServices.getAllOrderItems(orderId);
		modelAndView.addObject("allItems", allItems);
		modelAndView.setViewName("orderDetails");				
		return modelAndView;
		
	}
	
	  @GetMapping("/updateOrderStatus")
	    public ModelAndView updateOrderStatus(@RequestParam long orderId, 
	                                          @RequestParam String orderComment, 
	                                          @RequestParam OrderStatus orderStatus) {
		  
		   try {
		        // Assuming UTF-8 encoding for Arabic text, you might need to adjust if using a different encoding
		        orderComment = new String(orderComment.getBytes("ISO-8859-1"), "UTF-8");
		    } catch (UnsupportedEncodingException e) {
		        // Handle encoding exception if needed
		        e.printStackTrace();
		    }
		    
	        orderServices.updateOrderStatusAndComment(orderId, orderComment, orderStatus);
	        // Redirect to the orderDetails page with the same orderId
	        return new ModelAndView("redirect:/orderDetails?orderId=" + orderId);
	    }
}
