package com.watad.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
@Table(name="subCategory" , uniqueConstraints = {@UniqueConstraint(columnNames = {"categoryId","subCategoryName"})})
public class SubCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private long id ;
	
	@Column(name ="subCategoryName")
	private String subCategoryName;
	
	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;

	@OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY ,mappedBy = "subCategory")
	@JsonIgnore
	private List<Item> items;

	public SubCategory() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public Category getCategory() {
		return category;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public void setCategory(Category category) {
		this.category = category;
	}


	public void setId(long id) {
		this.id = id;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
