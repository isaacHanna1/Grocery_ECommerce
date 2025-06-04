package com.watad.controllers;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.watad.model.User;
import com.watad.services.UserService;

@RestController
public class UserController {
    private static final Logger logger = LogManager.getLogger(MarketplaceController.class);

	@Autowired
	private UserService userService;
	
	@PutMapping("/updateAddress")
	public ResponseEntity<Map<String, String>> updateUserAddress(@RequestBody User user){		
		String goverment = user.getGovernment();
		String city = user.getCity();
		String userAddrress = user.getUserAddress();
		userService.changeUserAddress(user.getId(), goverment, city, userAddrress);
		// Create a response entity with a success message
        Map<String, String> response = new HashMap<>();
        response.put("message", "User address updated successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	 @GetMapping("/getUserRole/{userID}")
	    public String getUserRole(@PathVariable long userID) {
		 logger.info("we starting to get user :"+userID);
	       String user_role = userService.getUserRole(userID);
	       logger.info("the user role is "+user_role);
	       return "{\"role\": \"" + (user_role != null ? user_role : "USER") + "\"}";
	    }
	 
	   @GetMapping("/findEmailByPhone")
	    public String findEmailByPhone(@RequestParam("phone") String phone) {
	        return userService.getEmailByPhone(phone);
	    }

}
