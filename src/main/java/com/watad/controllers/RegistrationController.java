package com.watad.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import com.watad.Dao.UserDao;
import com.watad.Dto.RegistrationDto;


@RestController
@Validated
public class RegistrationController {

	@Autowired
	private UserDao userDao;
	
	@GetMapping("/signUp")
	@Transactional
	public ModelAndView getRegisteration() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("signUp");
		return modelAndView;
	}
	
	@PostMapping("/add-user")
	public ModelAndView Save(@Valid @ModelAttribute RegistrationDto dto, BindingResult result) {
		ModelAndView modelAndView;
		if(result.hasErrors()) {
			modelAndView = new  ModelAndView();
			List<FieldError> fieldErrors = result.getFieldErrors();
			modelAndView.addObject("fieldErrors",fieldErrors);
			modelAndView.addObject("register",dto);
			modelAndView.setViewName("signUp");
			return modelAndView;
		}else {
		try {
			userDao.saveUser(dto);
			modelAndView = new  ModelAndView("redirect:/login");
			return modelAndView;
		}catch(ConstraintViolationException  ex) {
			List<FieldError> errors = processConstraintViolations(ex);
			System.out.print("size : "+errors.size());
	        modelAndView = new  ModelAndView();
			modelAndView.addObject("fieldErrors", errors);
			modelAndView.addObject("register",dto);
			modelAndView.setViewName("signUp");
			return modelAndView;
			

		}
		
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
}
