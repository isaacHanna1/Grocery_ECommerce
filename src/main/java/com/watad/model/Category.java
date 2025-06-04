package com.watad.model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ;
	
	
	@Column(name = "categoryName" , unique = true)
	private String categoryName ;
	

	@OneToMany(mappedBy = "category" , cascade = CascadeType.ALL)
	@JsonIgnore
	private List<SubCategory> subCategory;
	
	
	public Category() {	
	}
	
	public Category(long id) {
		this.id = id;
	}
	
	public Category(long id, String categoryName) {
		super();
		this.id = id;
		this.categoryName = categoryName;
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<SubCategory> getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(List<SubCategory> subCategory) {
		this.subCategory = subCategory;
	} 
	

    
	
}
