package com.watad.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.watad.Dao.CategoryDao;
import com.watad.Dao.SubCategoryDao;
import com.watad.Dao.UnitDao;
import com.watad.Dao.itemDao;
import com.watad.Dto.ItemDTOSearch;
import com.watad.Dto.ItemDto;
import com.watad.model.Category;
import com.watad.model.ImageDeleteRequest;
import com.watad.model.Item;
import com.watad.model.SubCategory;
import com.watad.model.Unit;
import com.watad.model.itemImages;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
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
	
	
	@GetMapping("/allItems")
	public ResponseEntity<List<ItemDto>> getAllItems() {
	    try {
	        List<ItemDto> allItems = itemDao.getAllItems();
	        if (allItems.isEmpty()) {
	            return ResponseEntity.noContent().build(); // Return 204 No Content if no items are found
	        }
	        return ResponseEntity.ok(allItems); 
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Return 500 Internal Server Error
	    }
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
	@PostMapping("/addItem")
	public ModelAndView addItem(@ModelAttribute Item item, @RequestParam("imageFile") MultipartFile image) throws IOException {
		if (image != null && !image.isEmpty()) {
			item.setImage(image.getBytes());
		}
		itemDao.addingNewItem(item);
		return new ModelAndView("redirect:/image/" + item.getId());
	}
	
	@PostMapping(path = "/addItemImages/{itemId}")
	public ModelAndView addItemImages(@PathVariable("itemId") long itemId, @RequestParam("images") MultipartFile[] images) throws IOException {		
		itemDao.uploadImages(itemId, images);
		ModelAndView modelAndView = new ModelAndView("redirect:/image/"+itemId);
	    return modelAndView;
	}
	
	@DeleteMapping(path="/delSubImage/{imageId}")
	public ResponseEntity<String> deleteImageFromSubImage(@PathVariable("imageId") long imageId) {
		boolean deleted =  itemDao.deleteItemImage(imageId);
        if (deleted) {
            return ResponseEntity.ok("Item deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found or could not be deleted");
        }
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
	
	@GetMapping(path="/showItem/{id}")
	public ModelAndView showItebBYId(@PathVariable("id") long itemId,ModelAndView modelAndView) {
		System.out.println("im here");
		modelAndView.setViewName("editItem");
		ItemDto item = itemDao.getItemById(itemId);
		List<Unit> allUnits  = unitDao.getAllUnit();
		List<Category> allCategory  = categoryDao.getListOfCategory();
		modelAndView.addObject("unit", allUnits);
		modelAndView.addObject("category", allCategory);
		System.out.println("now all things ok !");
		System.out.println(item.getCategoryName());

		List<SubCategory> subcategoryList =subCategoryDao.getSubCategoryInGategory(item.getCategoryName());
		System.out.println(subcategoryList.size());

		modelAndView.addObject("subCategory", subcategoryList);
		modelAndView.addObject("item", item);
		System.out.println("now all things ok !");
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
	        List<ImageDeleteRequest> imageDeleteRequest = new ArrayList<>();
	        List<itemImages> allSubImages = itemDao.getSubImages(itemId);

	     // Convert each itemImage to a byte array and then to Base64
	     for (itemImages itemImage : allSubImages) {
	         byte[] imageBytes = itemImage.getImage(); // Assuming you have a method to get the image bytes from itemImages
	         String base64Image = Base64.getEncoder().encodeToString(imageBytes);
	         ImageDeleteRequest image = new ImageDeleteRequest(itemImage.getId(),base64Image); 
	         imageDeleteRequest.add(image);
	     }
	        ItemDto itemDto = itemDao.getItemById(itemId);
        	modelAndView.addObject("itemDto", itemDto);
        	modelAndView.addObject("subImages", imageDeleteRequest); // Pass the list of Base64-encoded images to the view
		    return modelAndView;
	}
	

    @GetMapping("/subImages/{itemId}")
    public ResponseEntity<List<String>> getSubImagesForItem(@PathVariable("itemId") long itemId) {
        // Retrieve sub images for the specified item ID from the database
         List<itemImages> subImage= itemDao.getSubImages(itemId);
         List<String> base64SubImages = new ArrayList<>();
        // Check if sub images were found
        if (subImage != null && !subImage.isEmpty()) {
            // Convert byte arrays to base64 strings
            for (itemImages image: subImage) {
                String base64Image = Base64.getEncoder().encodeToString(image.getImage());
                base64SubImages.add(base64Image);
            }	

           
        }
        // Return base64 encoded sub images in the response body
        return ResponseEntity.ok(base64SubImages);
    }

	@PostMapping(path="/updateImageItem/{ItemId}")
	public ModelAndView updateingImage(@ModelAttribute Item item ,@RequestParam("image") MultipartFile image,@PathVariable("ItemId") long ItemId) throws IOException {
		byte[] imageBytes = image.getBytes();
		itemDao.updateImage(imageBytes, ItemId);
		ModelAndView modelAndView = new ModelAndView("redirect:/allItems/1");
		return modelAndView;
	}

	@GetMapping("/newArrival")
	public List<Item> getNewArrivalFromItems(){
		return itemDao.getNewArrival();
	}
	
	@GetMapping("/search")	
	public ResponseEntity<List<ItemDTOSearch>> searchItem(@RequestParam String partOfItemName) throws UnsupportedEncodingException{
	    partOfItemName = URLDecoder.decode(partOfItemName, "UTF-8");
        System.out.println("Received partOfItemName: " + partOfItemName); // Debugging line
		List<ItemDTOSearch> items = itemDao.getItemsAsIDAndName(partOfItemName);
        return ResponseEntity.ok(items);
	}
	@GetMapping("/getItemById/{id}")
	public ItemDto getItemById(@PathVariable("id") Long id) {
	    return itemDao.getItemById(id);
	}
	
	@GetMapping("/getItemPage/{id}")
	public ModelAndView getItemPage(@PathVariable("id") long itemId, ModelAndView modelAndView) throws UnsupportedEncodingException {	
		modelAndView.setViewName("productView");
		  byte[] imageData = itemDao.getImageDataById(itemId);
	        if (imageData != null) {
	        	
	        	byte[] encodeBase64 = Base64.getEncoder().encode(imageData);
	        	String base64Encoded = new String(encodeBase64, "UTF-8");
	        	modelAndView.addObject("base64Encoded", base64Encoded);
	        	ItemDto itemDto = itemDao.getItemById(itemId);
	        	modelAndView.addObject("item", itemDto);
	        }
	        List<ImageDeleteRequest> imageDeleteRequest = new ArrayList<>();
	        List<itemImages> allSubImages = itemDao.getSubImages(itemId);

	     // Convert each itemImage to a byte array and then to Base64
	     for (itemImages itemImage : allSubImages) {
	         byte[] imageBytes = itemImage.getImage(); // Assuming you have a method to get the image bytes from itemImages
	         String base64Image = Base64.getEncoder().encodeToString(imageBytes);
	         ImageDeleteRequest image = new ImageDeleteRequest(itemImage.getId(),base64Image); 
	         imageDeleteRequest.add(image);
	     }
	        ItemDto itemDto = itemDao.getItemById(itemId);
	        modelAndView.addObject("subImages", imageDeleteRequest); // Pass the list of Base64-encoded images to the view
		    return modelAndView;
	}
	
}
