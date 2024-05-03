package dev.haroon.blog.blogging.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.haroon.blog.blogging.payloads.ApiResponse;
import dev.haroon.blog.blogging.payloads.CategoryDTO;
import dev.haroon.blog.blogging.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
		
		return ResponseEntity.ok(categoryService.createCategory(categoryDTO));
	}

	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, 
			                                  @PathVariable Integer categoryId) {
		return ResponseEntity.ok(categoryService.update(categoryDTO, categoryId));
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer categoryId) {
		return ResponseEntity.ok(categoryService.findById(categoryId));
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDTO>> getAllCategory() {
		return ResponseEntity.ok(categoryService.getCategories());
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
		categoryService.delete(categoryId);
		return ResponseEntity.ok(new ApiResponse("Category Deleted Successfully", true));
	}
}
