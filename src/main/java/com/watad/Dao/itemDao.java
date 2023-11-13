package com.watad.Dao;

import java.util.List;

import com.watad.Dto.ItemDto;
import com.watad.model.Item;
public interface itemDao {

	public Item addingNewItem(Item item);
	public Item getItem(long id);
	public List<ItemDto> getAllItems();
	public List<ItemDto> getSpecificItems(int start);
	public List<ItemDto> getSpecificItemsForCustomers(int start, long categoryID , long subCategoryId);
//	public List<ItemDto> getSpecificItemsForTrader(int start,long categoryID , long subCategoryId);
	public long getCountOfRecordOfItems();
	public long getCountOfRecOfItems(long categoryID , long subCategoryID);
	public Item deleteItem(long id);
	public ItemDto getItemById(long id);
    public byte[] getImageDataById(long itemId);
    public void updateImage (byte[]image,long itemID);
    public Item updateItem(long itemId , Item item);

}
