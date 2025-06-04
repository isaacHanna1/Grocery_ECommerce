package com.watad.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.watad.model.CartItem;

@RestController
@RequestMapping("/cart")
public class CartController {

    private static final Logger logger = LogManager.getLogger(MarketplaceController.class);

	
	@PostMapping("/add")
	public Map<String, String> addItem(@RequestParam int    id,
						  @RequestParam String itemName,
						  @RequestParam double price,
						  @RequestParam String imgSrc,
						  @RequestParam double qunatity , HttpSession session) {
		Map<String ,String> response = new HashMap<>();
		if(itemName == "" || itemName == null ) {
			response.put("status" ,"error");
			response.put("message", "item name is reqired");
			return response;
		}
		 if (price <= 0 || qunatity <= 0) {
			 response.put("status", "error");
		     response.put("message", "Price and quantity must be positive");
		     return response;
		}
		
		
		List<CartItem>   cart  = (List<CartItem>)session.getAttribute("cart");
		if(cart == null) {
			    cart           = new ArrayList<>();
			    session.setAttribute("cart", cart);		
		}
		int founded            = itemIsExists(cart, id);
		if(founded != -1) {
		 double currQuantity   = cart.get(founded).getQuantity();
		        currQuantity   = currQuantity + qunatity;
		        cart.get(founded).setQuantity(currQuantity);
		}else {
			cart.add(new CartItem(id,itemName,price,qunatity,imgSrc));
		}
		 // Prepare response
		logger.info("the cart is :-> "+cart.get(0).getItemID());
	    response.put("status", "success");
	    response.put("message", "Item added to cart");
	    response.put("cartSize", cart.size()+"");
	    return response;
		
	}
	@GetMapping("/items")
	public List<CartItem> getCartItems(HttpSession session){
		  logger.info("Fetching cart items... ");
		  logger.info("Fetching cart items.... "+session.getId());
		    List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
		    if (cart == null) {
		        cart = new ArrayList<>();
		        session.setAttribute("cart", cart);
		        logger.info("Cart was null, initialized empty list.");
		    } else {
		        logger.info("Cart contains: " + cart.size() + " items.");
		    }
		    return cart;
	}
	
	@GetMapping("/length")
	public Map<String, String> getCartSize(HttpSession session){
		List<CartItem>   cart     = (List<CartItem>)session.getAttribute("cart");
		Map<String, String> sizeResponse  = new HashMap<>();
		if(cart == null ) {
			 sizeResponse.put("size","0");
			 return sizeResponse;
		}
		     sizeResponse.put("size",cart.size()+"");
		     return sizeResponse;
	}
	
	@DeleteMapping("/del")
	public String removeItem(HttpSession session) {
		session.removeAttribute("cart");
		return "cleared";
	}
	
	private int itemIsExists(List<CartItem> items , int id) {
		int found = -1 ; 
		for(int i = 0; i<items.size() ; i++ ) {
			if (items.get(i).getItemID() == id ) {
				found = i;
				return found;
			}
		}
		return found; 
	}
	
	@DeleteMapping("/del/{itemID}")
	private void deleteItemFromCart(HttpSession session,@PathVariable long itemID ) {

		logger.info("the item id is "+itemID);
		try {
		List<CartItem> items = (List<CartItem>) session.getAttribute("cart");
		if(items != null) {
		Iterator<CartItem> iterator = items.iterator();
		while (iterator.hasNext()) {
		    CartItem item = iterator.next();
		    if (item.getItemID() == itemID) {
		        iterator.remove();  
		    }
		}
		}
		}catch (Exception e) {
			logger.error("error happened "+e.getMessage());
			e.getStackTrace();
		}
	}
	private void printCartItem(HttpSession session) {
		List<CartItem>   cart  = (List<CartItem>)session.getAttribute("cart");
		if(cart == null) {
		    cart           = new ArrayList<>();
		    session.setAttribute("cart", cart);		
		}
	}
}
