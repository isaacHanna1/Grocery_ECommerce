package com.watad.Dto;

public class ItemDTOSearch {

	
	private long id ;
	private String itemName ;
	
	
	
	
	public ItemDTOSearch(long id, String itemName) {
		super();
		this.id = id;
		this.itemName = itemName;
	}
	
	
	public long getId() {
		return id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	
	
}
