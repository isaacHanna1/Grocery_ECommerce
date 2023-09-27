package com.watad.controllers;



import java.io.UnsupportedEncodingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.watad.Dao.CategoryDao;
import com.watad.api.APiResponse;
import com.watad.model.Category;

import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Transactional
@RestController
public class CategoryController {

	// this instance,initalized by spring container
	// to use db function in categoryDaoImp
	@Autowired
	private CategoryDao categoryDao; 

	
	// this function for geting jsp page for category page
	@GetMapping(path = "/categoryPage")
	public ModelAndView retrivingCategoryPage(ModelAndView modelAndView) {
		List allCategories = categoryDao.getListOfCategory();
		modelAndView.setViewName("addsection");
		modelAndView.addObject("allCategories",allCategories);
		return modelAndView;
	}

	// function for saving new category  
	@PostMapping("/addCategory")
	public ModelAndView AddCategory(Category category) {
		ModelAndView modelAndView = new ModelAndView("redirect:/categoryPage");
		categoryDao.insertNewCategory(category);
		List<Category> allCategories = categoryDao.getListOfCategory();
		modelAndView.addObject("allCategories", allCategories);
		return modelAndView;		
	}
	/* this api function to delete category from database
	 * i will use  by javascript to delete the record 
	*/
	@DeleteMapping("/categoryApi/deleteCategory/{id}")
	public Category deleteCategory(@PathVariable Long id) {
		Category deletedCategory = categoryDao.getCategory(id);
	    categoryDao.deleteCategory(id);
	    return deletedCategory;
	}
	
	@PutMapping("/categoryApi/editCategory")
	public Category updateCategory(Category category) {
		return categoryDao.editCategory(category);
		
	}


}
