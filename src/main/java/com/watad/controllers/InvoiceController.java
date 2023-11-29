package com.watad.controllers;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.watad.model.Category;

@RestController
@Transactional
public class InvoiceController {

	@GetMapping("/invoice")
	public ModelAndView showInvoice(ModelAndView modelAndView) {
		modelAndView.setViewName("invoice");
		return modelAndView;
	}
	
}
