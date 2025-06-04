package com.watad.model;

import java.sql.Date;
import java.util.Base64;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "item")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "itemName", unique = true)
	private String itemName;

	@Lob
	@Column(name = "image", columnDefinition = "LONGBLOB")
	private byte[] image;
	
    @JsonIgnore
	@OneToMany(mappedBy = "item" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	private List<itemImages> itemImage;

	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "subCategoryId")
	private SubCategory subCategory;

	@ManyToOne
	@JoinColumn(name = "unitId")
	private Unit unit;

	@Column(name = "purchasePrice")
	private float purchasePrice;

	@Column(name = "profitMarginCustomer")
	private float profitMarginCustomer;

	@Column(name = "profitMarginTrader")
	private float profitMarginTrader;

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

	@Column(name = "itemDescription", length = 1000)
	private String itemDescription;

	@Column(name = "avability")
	private boolean avability;

	@JsonIgnore
	@OneToMany(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OrderItems> orderItemsList;

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

	
	public List<itemImages> getItemImage() {
		return itemImage;
	}

	public void setItemImage(List<itemImages> itemImage) {
		this.itemImage = itemImage;
	}

	public byte[] getImage() {
		return image;
	}
	
	public String getImageAsBase64() {
	    if (image != null && image.length > 0) {
	        return Base64.getEncoder().encodeToString(image);
	    }
	    return null; // or return an empty string if image is null or empty
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public List<OrderItems> getOrderItemsList() {
		return orderItemsList;
	}

	public void setOrderItemsList(List<OrderItems> orderItemsList) {
		this.orderItemsList = orderItemsList;
	}

	public  float calcPrice() {
		float purchasePrice = this.purchasePrice;
		float marginProfit  = profitMarginCustomer;
		float price = ((purchasePrice * marginProfit) / 100 ) + purchasePrice;
		return price ;
	}
	public  float calcPrice_trader() {
		float purchasePrice = this.purchasePrice;
		float marginProfit  = profitMarginTrader;
		float price = ((purchasePrice * marginProfit) / 100 ) + purchasePrice;
		System.out.println("the trader price"+this.getItemName()+" : "+price);
		return price ;
	}
	
	public float calcPriceAfterDiscount() {
			if(discountPercentageCustomer == 0) {
				return calcPrice();
			}
			float currentPrice = calcPrice();
			float discountPercentage = discountPercentageCustomer/100;
			float finalPrice = currentPrice - (currentPrice * discountPercentage);
			return finalPrice;
	}
	
	// for trader
	public float calcPriceAfterDiscount_trader() {
		if(discountPercentageTrader == 0) {
			return calcPrice_trader();
		}
		float currentPrice = calcPrice_trader();
		float discountPercentage = this.discountPercentageTrader/100;
		float finalPrice = currentPrice - (currentPrice * discountPercentage);
		return finalPrice;
}
}
