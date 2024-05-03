package dev.haroon.blog.blogging.service;

import java.util.List;

import dev.haroon.blog.blogging.entities.Category;
import dev.haroon.blog.blogging.payloads.CategoryDTO;

public interface CategoryService {

	CategoryDTO createCategory(CategoryDTO category);

	CategoryDTO findById(Integer id);

	CategoryDTO update(CategoryDTO category, Integer id);

	void delete(Integer id);

	List<CategoryDTO> getCategories();
	
	Category dtoToCategory(CategoryDTO dto);
	
	CategoryDTO categoryToDTO(Category category);
}
