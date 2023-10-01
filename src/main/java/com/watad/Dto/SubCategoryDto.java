package com.watad.Dto;

public class SubCategoryDto {
	
	private long id ;
	private String subCategoryName;
	private String categoryName;
	
	public SubCategoryDto(long id, String subCategoryName, String categoryName) {
		super();
		this.id = id;
		this.subCategoryName = subCategoryName;
		this.categoryName = categoryName;
	}
	public long getId() {
		return id;
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	

}
