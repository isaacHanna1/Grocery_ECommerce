package com.watad.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ;
	@Column(name = "categoryName")
	private String categoryName ;
	
	
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
	
	
	
	
}
