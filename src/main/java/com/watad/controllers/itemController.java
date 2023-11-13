package com.watad.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.watad.Dao.CategoryDao;
import com.watad.Dao.SubCategoryDao;
import com.watad.Dao.UnitDao;
import com.watad.Dao.itemDao;
import com.watad.Dto.ItemDto;
import com.watad.Dto.SubCategoryDto;
import com.watad.model.Category;
import com.watad.model.Item;
import com.watad.model.SubCategory;
import com.watad.model.Unit;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;


@RestController
@Transactional
public class itemController {

	@Autowired
	private UnitDao unitDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private SubCategoryDao subCategoryDao;
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
	
	@GetMapping("/allItems/{start}")
	public ModelAndView showAllIems(ModelAndView modelAndView,@PathVariable int start) {
		modelAndView.setViewName("showItems");
		List<ItemDto>allItem = itemDao.getSpecificItems(start);
		modelAndView.addObject("allItem", allItem);
		long record = itemDao.getCountOfRecordOfItems();
		modelAndView.addObject("recordCount", record);
		return modelAndView;
	}
	@PostMapping(path = "/addItem")
	public ModelAndView addingItem(@ModelAttribute Item item ,@RequestParam("image") MultipartFile image) throws IOException {
		 byte[] imageBytes = image.getBytes();
         item.setImage(imageBytes);
		 itemDao.addingNewItem(item);
		 ModelAndView modelAndView = new ModelAndView("redirect:/allItems/1");
		 return modelAndView;
	}
	@RequestMapping(path="/editItem/{itemId}")
	public ModelAndView updateItem(@ModelAttribute Item item , @PathVariable Long itemId) {
		itemDao.updateItem(itemId, item);
		ModelAndView modelAndView = new ModelAndView("redirect:/allItems/1");
		return modelAndView;
	}
	@DeleteMapping(path = "/delItem/{id}")
	public Item deleteItem(@PathVariable Long id) {
	    return itemDao.deleteItem(id);
	}
	
	@GetMapping(path="/editItem/{id}")
	public ModelAndView showItebBYId(@PathVariable Long id,ModelAndView modelAndView) {
		modelAndView.setViewName("editItem");
		ItemDto item = itemDao.getItemById(id);
		List<Unit> allUnits  = unitDao.getAllUnit();
		List<Category> allCategory  = categoryDao.getListOfCategory();
		modelAndView.addObject("unit", allUnits);
		modelAndView.addObject("category", allCategory);
		List<SubCategory> subcategoryList =subCategoryDao.getSubCategoryInSuchGategory(item.getCategoryName());
		modelAndView.addObject("subCategory", subcategoryList);
		modelAndView.addObject("item", item);
		return modelAndView;
	}
	@GetMapping("/image/{itemId}")
	public ModelAndView serveImage(@PathVariable Long itemId,ModelAndView modelAndView) throws UnsupportedEncodingException {
		
		modelAndView.setViewName("itemImage");
		  byte[] imageData = itemDao.getImageDataById(itemId);

	        if (imageData != null) {
	        	
	        	byte[] encodeBase64 = Base64.getEncoder().encode(imageData);
	        	String base64Encoded = new String(encodeBase64, "UTF-8");
	        	modelAndView.addObject("base64Encoded", base64Encoded);
	        	ItemDto itemDto = itemDao.getItemById(itemId);
	        	modelAndView.addObject("itemDto", itemDto);
	        }
	        ItemDto itemDto = itemDao.getItemById(itemId);
        	modelAndView.addObject("itemDto", itemDto);	        
		    return modelAndView;
	}
	@PostMapping(path="/updateImageItem/{ItemId}")
	public ModelAndView updateingImage(@ModelAttribute Item item ,@RequestParam("image") MultipartFile image,@PathVariable("ItemId") long ItemId) throws IOException {
		byte[] imageBytes = image.getBytes();
		itemDao.updateImage(imageBytes, ItemId);
		ModelAndView modelAndView = new ModelAndView("redirect:/allItems/1");
		return modelAndView;
	}

}
