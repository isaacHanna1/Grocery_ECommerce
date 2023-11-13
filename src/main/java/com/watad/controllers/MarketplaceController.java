package com.watad.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.watad.Dao.CategoryDao;
import com.watad.Dao.SubCategoryDao;
import com.watad.Dao.itemDao;
import com.watad.Dto.ItemDto;
import com.watad.model.Category;
import com.watad.model.SubCategory;


@Transactional
@RestController
public class MarketplaceController {
	
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private SubCategoryDao subCategoryDao;
	
	@Autowired
	private itemDao itemDao;
	
	
	@GetMapping("/marketPlace")
	public ModelAndView getMarketPlace(ModelAndView modelAndView) {
		List<Category>allCategories = categoryDao.getListOfCategory();
		modelAndView.setViewName("index");
		modelAndView.addObject("allCategories",allCategories);
		return modelAndView;
	}
	
	@GetMapping("/subCategory/{categoryId}")
	public List<SubCategory> gettingSubCategory(@PathVariable long categoryId){
		List<SubCategory> sub =  subCategoryDao.getSubCategoryInSuchGategory(categoryId);
		if(sub.size() == 0) {
			return null;
		}
		return sub;
	}
	
	@GetMapping("/items/{pageNumber}/{categoryID}/{subCategoryId}")
	public List<ItemDto> gettingItemInSubCategory(@PathVariable int pageNumber ,@PathVariable long categoryID ,@PathVariable  long subCategoryId){
		List<ItemDto> items =  itemDao.getSpecificItemsForCustomers(pageNumber, categoryID, subCategoryId);
		return items;
	}

	@GetMapping("/itemsInSuchCategoryAndSubCategory/{categoryId}/{subCategoryId}")
	public long getNumberOfItemsInSuchCategoryAndsubcategory
	(@PathVariable int categoryId , @PathVariable int subCategoryId ) {
		return itemDao.getCountOfRecOfItems(categoryId, subCategoryId);
	}
}
