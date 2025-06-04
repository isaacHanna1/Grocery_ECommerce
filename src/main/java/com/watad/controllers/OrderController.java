package com.watad.controllers;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.watad.model.Order;
import com.watad.model.OrderRequestHolder;
import com.watad.model.OrderStatus;
import com.watad.services.OrderServices;

@RestController
public class OrderController {
	
	@Autowired
	private OrderServices orderServices;
	
	@PostMapping("/newOrder")
	public ResponseEntity<Map<String, String>> makeOrder(@RequestBody OrderRequestHolder orderRequestHolder) {
		
		
	    // Call the service method to create the order
		System.err.println("the order user "+orderRequestHolder.getOrder().getUser().getId());
	    orderServices.makeOrder(orderRequestHolder.getOrder(), orderRequestHolder.getOrderItems());
	    // Construct a response message
	    String responseMessage = "Order created successfully";

	    // Create a Map to hold the response message
	    Map	<String, String> response = new HashMap<>();
	    response.put("message", responseMessage);

	    // Return a ResponseEntity with the response Map and a HTTP status code of 200 (OK)
	    return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(path = "/delOrder/{orderId}")
	public String deleteOrder(@PathVariable long orderId) {
		orderServices.deleteOrder(orderId);
		return " the delete completed sucessfully ";
	}
	
	
	@GetMapping("/allOrders")
	public ModelAndView allOrders(@RequestParam(defaultValue = "0") int pageNumber) {
		
		ModelAndView modelAndView = new ModelAndView();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1); // Subtract 1 day to get yesterday's date
		Date yesterday = calendar.getTime();
		Date today = new Date();
		
	    List<Order> allOrder =orderServices.findWhere(pageNumber, yesterday,  today ,OrderStatus.wait);
		String formattedFrom = getDateInFormat(yesterday);
		String formattedTo = getDateInFormat(today);

		modelAndView.addObject("dateFrom", formattedFrom);
	    modelAndView.addObject("dateTo", formattedTo);
		modelAndView.addObject("status", OrderStatus.wait);
	    modelAndView.addObject("allOrder", allOrder);
	    long orderCount = orderServices.getOrdercount();
	    modelAndView.addObject("orderCount", orderCount);
	    modelAndView.addObject("endPoint", "allOrders");
	    modelAndView.setViewName("allOrders");
        
		return modelAndView;
	}
	// if  i want to use it as api 
	@GetMapping("/orderWhere/{pageNumber}/{from}/{to}/{status}")
	public ModelAndView allOrders(@PathVariable int pageNumber,
	                              @PathVariable Date from,
	                              @PathVariable Date to,
	                              @PathVariable OrderStatus status) {
	    ModelAndView modelAndView = new ModelAndView();
	    
	    // Get the list of orders based on the provided criteria
	    List<Order> filteredOrders = orderServices.findWhere(pageNumber, from, to, status);

	    String formattedFrom = getDateInFormat(from);
	    String formattedTo = getDateInFormat(from);

	    modelAndView.addObject("dateFrom", formattedFrom);
	    modelAndView.addObject("dateTo", formattedTo);
	    modelAndView.addObject("status", status);
	    
	    modelAndView.addObject("allOrder", filteredOrders);
		modelAndView.setViewName("allOrders");
	    
	    return modelAndView;
	}
	
	@GetMapping("/orderWhere")
	public ModelAndView filterOrders(@RequestParam(required = false, defaultValue = "0") int pageNumber,
	                                  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
	                                  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date to,
	                                  @RequestParam(required = false) OrderStatus status) {
	    ModelAndView modelAndView = new ModelAndView();
	    
	    // Get the list of orders based on the provided criteria
	    List<Order> filteredOrders = orderServices.findWhere(pageNumber, from, to, status);
	    
	    modelAndView.addObject("allOrder", filteredOrders);
	    
	    String formattedFrom = getDateInFormat(from);
	    String formattedTo   = getDateInFormat(to);

	    modelAndView.addObject("dateFrom", formattedFrom);
	    modelAndView.addObject("dateTo", formattedTo);
	    modelAndView.addObject("status", status);
	    long orderCount =filteredOrders.size();
	    modelAndView.addObject("orderCount", orderCount);
	    modelAndView.addObject("endPoint", "orderWhere");
	    modelAndView.setViewName("allOrders");
	    
	    return modelAndView;
	}
	

	

	
	public String getDateInFormat(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	    String formattedDate = date != null ? dateFormat.format(date) : null;
	    return formattedDate;
	}
	
	

}
