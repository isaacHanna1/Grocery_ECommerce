package com.watad.model;

public class CartItem {
	
	private int    itemID;
	private String itemName;
	private String imgSrc;
	private double price;
	private double quantity;
	
	
	
	
	public CartItem(int itemID, String itemName, double price, double quantity , String imgSrc) {
		super();
		this.itemID   = itemID;
		this.itemName = itemName;
		this.price    = price;
		this.quantity = quantity;
		this.imgSrc   = imgSrc;
	}
	public int getItemID() {
		return itemID;
	}
	public String getItemName() {
		return itemName;
	}
	public double getPrice() {
		return price;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	
	

}
