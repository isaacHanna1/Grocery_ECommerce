	package com.watad.model;
	
	import java.sql.Date;
	
	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.JoinColumn;
	import javax.persistence.Lob;
	import javax.persistence.ManyToOne;
	import javax.persistence.Table;
	
	@Entity
	@Table(name = "item")
	public class Item {
	
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id ;
		
		@Column(name = "itemName" , unique = true)
		private String itemName;
		
		@Lob
		@Column(name="image" , columnDefinition = "LONGBLOB")
		private byte[] image;
		
		@ManyToOne
		@JoinColumn(name="categoryId")
		private Category category;
		
		@ManyToOne
		@JoinColumn(name="subCategoryId")
		private SubCategory subCategory;
		
		@ManyToOne
		@JoinColumn(name="unitId")
		private Unit unit ;
		
		@Column(name = "purchasePrice")
		private float purchasePrice;
		
		@Column(name = "profitMarginCustomer")
		private float profitMarginCustomer ;
	
		@Column(name = "profitMarginTrader")
		private float profitMarginTrader ;
		
		@Column(name = "discountPercentageCustomer")
		private float discountPercentageCustomer;
		
		@Column(name = "discountPercentageTrader")
		private float discountPercentageTrader;
		
		@Column(name = "sellingPriceCustomer")
		private float sellingPriceCustomer;
		
		@Column(name = "sellingPriceTrader")
		private float sellingPriceTrader;
		
		@Column(name = "addingDate")
		private Date addingDate;
		
		@Column(name = "itemDescription" , length= 500)
		private String itemDescription ;
		
		@Column(name = "avability")
		private boolean avability;
	
		public long getId() {
			return id;
		}
	
		public String getItemName() {
			return itemName;
		}
	
	
		public Category getCategory() {
			return category;
		}
	
		public SubCategory getSubCategory() {
			return subCategory;
		}
	
		public Unit getUnit() {
			return unit;
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
	
	
		public void setCategory(Category category) {
			this.category = category;
		}
	
		public void setSubCategory(SubCategory subCategory) {
			this.subCategory = subCategory;
		}
	
		public void setUnit(Unit unit) {
			this.unit = unit;
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
	
		public byte[] getImage() {
			return image;
		}
	
		public void setImage(byte[] image) {
			this.image = image;
		}
	
	
		
		
		
	}
