package com.watad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.watad.Dao.CategoryDao;
import com.watad.Dao.SubCategoryDao;
import com.watad.Dto.SubCategoryDto;
import com.watad.model.Category;
import com.watad.model.SubCategory;

import java.util.List;

@Transactional
@RestController
public class SubCategoryController {

	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private SubCategoryDao subCategoryDao;
	
	@GetMapping(path = "/subCategory")
	public ModelAndView gettingAllCategory(ModelAndView modelAndView) {
		 List<Category> listOfCategory = categoryDao.getListOfCategory();
		 List<SubCategoryDto> listOfSubCategory =subCategoryDao.allSubCategories();
		 modelAndView.setViewName("addSubSection");
		 modelAndView.addObject("allCategories", listOfCategory);
		 modelAndView.addObject("allSubCategories", listOfSubCategory);
		 return modelAndView;
	}
	
	@PostMapping(path = "/addSubCategory")
	public SubCategory addNewSubCategory(@RequestBody SubCategory subCategory) {
		return subCategoryDao.insertNewCategory(subCategory);
	}
	
	@GetMapping(path = "/getAllSubCategory")
	public List<SubCategoryDto> gettingAllsubCategory(){
		return subCategoryDao.allSubCategories();
	}
	
	@GetMapping(path = "/getNewId")
	public String getId() {
		String id = subCategoryDao.newId()+"";
		return "{\"id\":"+id+"}";
	}
	@DeleteMapping(path = "/deleteSubCategory/{id}")
	public String deleteSubCategory(@PathVariable long id) {
		subCategoryDao.deleteSubCategory(id);
		String deleted = ""+id;
	
		return "{\"deleted\":"+deleted+"}";
	}
}
