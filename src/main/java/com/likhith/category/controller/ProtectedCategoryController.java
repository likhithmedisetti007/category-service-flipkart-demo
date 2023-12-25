package com.likhith.category.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.likhith.category.dto.Product;
import com.likhith.category.service.CategoryService;

@RestController
@RequestMapping("/category/protected")
public class ProtectedCategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping("/refreshCategoryList")
	public String refreshCategoryList() {

		categoryService.loadDataFromMongoDB();

		return "CategoryList refreshed";
	}

	@PostMapping("/{categoryName}/{subCategoryName}/addProduct")
	public String addProduct(@PathVariable("categoryName") String categoryName,
			@PathVariable("subCategoryName") String subCategoryName, @RequestBody Product product) {
		return categoryService.addProduct(categoryName, subCategoryName, product);
	}

	@PutMapping("/{categoryName}/{subCategoryName}/updateProduct")
	public String updateProduct(@PathVariable("categoryName") String categoryName,
			@PathVariable("subCategoryName") String subCategoryName, @RequestBody Product product) {
		return categoryService.updateProduct(categoryName, subCategoryName, product);
	}

	@DeleteMapping("/{categoryName}/{subCategoryName}/deleteProduct")
	public String deleteProduct(@PathVariable("categoryName") String categoryName,
			@PathVariable("subCategoryName") String subCategoryName, @RequestBody Product product) {
		return categoryService.deleteProduct(categoryName, subCategoryName, product);
	}

}