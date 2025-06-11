package com.watad.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;

import com.watad.services.UrlManipulatorService;
import com.watad.services.UserService;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import com.watad.Dao.UserDao;
import com.watad.Dto.RegistrationDto;
import com.watad.model.User;
import com.watad.services.UrlManipulatorServiceImp;


@Controller
@Validated
public class RegistrationController {


	private final UserService userService;
	private  final UrlManipulatorService urlManipulatorService;

	public RegistrationController(UserService userService, UrlManipulatorService urlManipulatorService) {
		this.userService = userService;
		this.urlManipulatorService = urlManipulatorService;
	}

	@GetMapping("/signUp")
	public ModelAndView getRegisteration() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("signUp");
		return modelAndView;
	}
	
	@GetMapping("/userData/{userId}")
	public ModelAndView getRegisteration(@PathVariable long userId) {
		ModelAndView modelAndView = new ModelAndView();
		User user = userService.findById(userId);
		modelAndView.addObject("userData",user);
		modelAndView.setViewName("userData");
		return modelAndView;
	}
	
	@PostMapping("/add-user")
	public String Save(@Valid @ModelAttribute RegistrationDto dto, BindingResult result , Model model ,HttpServletRequest req) {
		
		if(result.hasErrors()) {
			List<FieldError> fieldErrors = result.getFieldErrors();
			model.addAttribute("fieldErrors", fieldErrors);
			model.addAttribute("register",dto);
			return "signUp";
		}else {
		try {
			User user = userService.findByEmail(dto.getEmail());
			System.out.println(user);
			if(user == null) {
				user = userService.findByPhone(dto.getPhone());
				if(user == null) {
					userService.saveUser(dto , req);
					return "login";
				}else {
					model.addAttribute("errorOf", " رقم الهاتف مسجل من قبل ");
					model.addAttribute("register",dto);
					return("signUp");
				}
			}
			else {
			  model.addAttribute("errorOf", " الايميل مسجل من قبل ");
			  model.addAttribute("register",dto);
			  return("signUp");
			}
		}catch(ConstraintViolationException  ex) {
			List<FieldError> errors = processConstraintViolations(ex);
			model.addAttribute("fieldErrors", errors);
			model.addAttribute("register",dto);
			return("signUp");

		}
	}
	}	
	@GetMapping("/active/{token}")
	public String activeUser(@PathVariable String token , Model model) {

		String url = urlManipulatorService.decrypt(token);
		long milliSeconds = Long.parseLong(urlManipulatorService.extractExpireDate(url));
		System.out.println("the mill:"+milliSeconds);
		if(isNotExpired(milliSeconds)) {
			String message = "لقد انتهت صلاحية التفعيل حاول مرة اخري";
			model.addAttribute("errorMessage", message);
			return "errorPage";
		}else {
			long id = Long.parseLong(urlManipulatorService.extractId(url));
			System.out.println("the id is :"+id);
			userService.activeUserAccount(id);
		    model.addAttribute("message", "تم التفعيل بنجاح");
		    return "login";
		}
	} 
	
	
	private List<FieldError> processConstraintViolations(ConstraintViolationException ex) {
	    Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
	    List<FieldError> fieldErrors = new ArrayList<>();
	    for (ConstraintViolation<?> violation : constraintViolations) {
	        String field = ((PathImpl) violation.getPropertyPath()).getLeafNode().getName();
	        String message = violation.getMessage();
	        fieldErrors.add(new FieldError("User", field, message));
	    }
	    return fieldErrors;
	}
	


    public static String decodeURL(String encodedUrl) {
        try {
            // Decode the URL using UTF-8 encoding
            return URLDecoder.decode(encodedUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // Handle decoding exception
            e.printStackTrace();
            return null;
        }
    }

    public boolean isNotExpired(long dateInMilliSeconds) {
    	
    	Date date = new Date(dateInMilliSeconds);
    	Date now = new Date();
    	int compareDate = date.compareTo(now);
    	if(compareDate < 0) {
    		return true ;
    	}else {
    		return false;
    	}
    }
}
