package com.watad.Dao;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.watad.Dto.ItemDTOSearch;
import com.watad.Dto.ItemDto;
import com.watad.model.Item;
import com.watad.model.itemImages;
public interface itemDao {

	public Item addingNewItem(Item item);
	public Item getItem(long id);
	public List<ItemDto> getAllItems();
	public List<ItemDto> getSpecificItems(int start);
	public List<ItemDto> getSpecificItemsForCustomers(int start, long categoryID , long subCategoryId , String userRole);
//	public List<ItemDto> getSpecificItemsForTrader(int start,long categoryID , long subCategoryId);
	public long getCountOfRecordOfItems();
	public long getCountOfRecOfItems(long categoryID , long subCategoryID);
	public Item deleteItem(long id);
	public ItemDto getItemById(long id);
    public byte[] getImageDataById(long itemId);
    public void updateImage (byte[]image,long itemID);
    public Item updateItem(long itemId , Item item);
    void uploadImages(long itemID, MultipartFile[] images) throws IOException;
    List<itemImages> getSubImages(long itemId);
    boolean deleteItemImage(long id);
    List<Item> getNewArrival();
    List<Item> getHighDiscounts();
    List<Item> getHighDiscounts_trader();
    List<ItemDTOSearch> getItemsAsIDAndName(String partOfItemName); // i used it in search bar just retrieve ID and Name of item  
    
}
