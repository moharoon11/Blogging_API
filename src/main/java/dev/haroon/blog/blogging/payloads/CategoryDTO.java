package dev.haroon.blog.blogging.payloads;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

public class CategoryDTO {
	
	
	private Integer categoryId;
	
	@NotBlank(message="Title must not be empty")
	private String categoryTitle;
	

	@NotBlank
	@Size(min=10, message="Desctiption must contain minimum of 10 characters")
	private String categoryDescription;


	public Integer getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}


	public String getCategoryTitle() {
		return categoryTitle;
	}


	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}


	public String getCategoryDescription() {
		return categoryDescription;
	}


	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

}
