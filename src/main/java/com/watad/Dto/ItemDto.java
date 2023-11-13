package com.watad.Dto;

import java.util.Date;

public class ItemDto {

	
	private long id ; 
	private String itemName;
	private byte[] image;
	private String categoryName;
	private String subCategoryName;
	private String unitName;
	private float purchasePrice;
	private float profitMarginCustomer ;
	private float profitMarginTrader ;
	private float discountPercentageCustomer;
	private float discountPercentageTrader;
	private float sellingPriceCustomer;
	private float sellingPriceTrader;
	private Date  addingDate;
	private String itemDescription ;
	private boolean avability;
	
	
	
	public ItemDto() {
		super();
	}

	public ItemDto(long id, String itemName, float purchasePrice, float sellingPriceCustomer,
			float sellingPriceTrader) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.purchasePrice = purchasePrice;
		this.sellingPriceCustomer = sellingPriceCustomer;
		this.sellingPriceTrader = sellingPriceTrader;
	}

	//constructor for getting item to show it to customers
	public ItemDto(long id, String itemName, byte[] image, String unitName, float discountPercentageCustomer,
			float sellingPriceCustomer, Date addingDate, String itemDescription, boolean avability) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.image = image;
		this.unitName = unitName;
		this.discountPercentageCustomer = discountPercentageCustomer;
		this.sellingPriceCustomer = sellingPriceCustomer;
		this.addingDate = addingDate;
		this.itemDescription = itemDescription;
		this.avability = avability;
	}
	public ItemDto(long id, String itemName, byte[] image, 
			String categoryName, String subCategoryName,
            String unitName, float purchasePrice,
            float profitMarginCustomer, float profitMarginTrader,
            float discountPercentageCustomer, 
            float discountPercentageTrader,
            float sellingPriceCustomer,
            float sellingPriceTrader, Date addingDate, 
            String itemDescription, boolean avability) {
			super();
			this.id = id;
			this.itemName = itemName;
			this.image = image;
			this.categoryName = categoryName;
			this.subCategoryName = subCategoryName;
			this.unitName = unitName;
			this.purchasePrice = purchasePrice;
			this.profitMarginCustomer = profitMarginCustomer;
			this.profitMarginTrader = profitMarginTrader;
			this.discountPercentageCustomer = discountPercentageCustomer;
			this.discountPercentageTrader = discountPercentageTrader;
			this.sellingPriceCustomer = sellingPriceCustomer;
			this.sellingPriceTrader = sellingPriceTrader;
			this.addingDate = addingDate;
			this.itemDescription = itemDescription;
			this.avability = avability;
	}

	

	public long getId() {
		return id;
	}


	public String getItemName() {
		return itemName;
	}

	public byte[] getImage() {
		return image;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public String getUnitName() {
		return unitName;
	}

	public float getPurchasePrice() {
		return purchasePrice;
	}

	public float getProfitMarginCustomer() {
		return profitMarginCustomer;
	}

	public float getProfitMarginTrader() {
		return profitMarginTrader;
	}

	public float getDiscountPercentageCustomer() {
		return discountPercentageCustomer;
	}

	public float getDiscountPercentageTrader() {
		return discountPercentageTrader;
	}


	public float getSellingPriceCustomer() {
		return sellingPriceCustomer;
	}



	public float getSellingPriceTrader() {
		return sellingPriceTrader;
	}



	public Date getAddingDate() {
		return addingDate;
	}



	public String getItemDescription() {
		return itemDescription;
	}



	public boolean isAvability() {
		return avability;
	}



	public void setId(long id) {
		this.id = id;
	}



	public void setItemName(String itemName) {
		this.itemName = itemName;
	}



	public void setImage(byte[] image) {
		this.image = image;
	}



	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}



	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}



	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}



	public void setPurchasePrice(float purchasePrice) {
		this.purchasePrice = purchasePrice;
	}



	public void setProfitMarginCustomer(float profitMarginCustomer) {
		this.profitMarginCustomer = profitMarginCustomer;
	}



	public void setProfitMarginTrader(float profitMarginTrader) {
		this.profitMarginTrader = profitMarginTrader;
	}



	public void setDiscountPercentageCustomer(float discountPercentageCustomer) {
		this.discountPercentageCustomer = discountPercentageCustomer;
	}



	public void setDiscountPercentageTrader(float discountPercentageTrader) {
		this.discountPercentageTrader = discountPercentageTrader;
	}



	public void setSellingPriceCustomer(float sellingPriceCustomer) {
		this.sellingPriceCustomer = sellingPriceCustomer;
	}



	public void setSellingPriceTrader(float sellingPriceTrader) {
		this.sellingPriceTrader = sellingPriceTrader;
	}



	public void setAddingDate(Date addingDate) {
		this.addingDate = addingDate;
	}



	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}



	public void setAvability(boolean avability) {
		this.avability = avability;
	}
	
}