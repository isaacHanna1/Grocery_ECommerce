package com.watad.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.watad.Dao.CategoryDao;
import com.watad.Dao.SubCategoryDao;
import com.watad.Dao.UnitDao;
import com.watad.Dao.itemDao;
import com.watad.Dto.SubCategoryDto;
import com.watad.model.Category;
import com.watad.model.Item;
import com.watad.model.SubCategory;
import com.watad.model.Unit;
import java.util.List;


@RestController
@Transactional
public class itemController {

	@Autowired
	private UnitDao unitDao;
	@Autowired
	private SubCategoryDao subCategoryDao;
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private itemDao itemDao;
	
	@GetMapping("/itemPage")
	public ModelAndView retrivingItemPage(ModelAndView modelAndView) {
		List<Unit> allUnits  = unitDao.getAllUnit();
		List<Category> allCategory  = categoryDao.getListOfCategory();
		modelAndView.setViewName("items");
		modelAndView.addObject("unit", allUnits);
		modelAndView.addObject("category", allCategory);
		
		return modelAndView;
	}
	@PostMapping(path = "/addItem")
	public ModelAndView addingItem(Item item) {
		ModelAndView modelAndView = new ModelAndView("redirect:/itemPage");
		 itemDao.addingNewItem(item);
		 return modelAndView;
	}
	
}
