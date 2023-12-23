package com.likhith.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.likhith.category.document.Category;
import com.likhith.category.dto.CategoryResponse;
import com.likhith.category.exception.ErrorMessage;
import com.likhith.category.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping("/protected/refreshCategoryList")
	public String refreshCategoryList() {

		categoryService.loadDataFromMongoDB();

		return "CategoryList refreshed";

	}

	@GetMapping("/public/getAllCategories")
	public ResponseEntity<CategoryResponse> getAllCategories() {

		List<String> categories = categoryService.getAllCategories();

		if (!CollectionUtils.isEmpty(categories)) {
			return ResponseEntity.ok().body(new CategoryResponse(categories));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
					.body(new CategoryResponse(new ErrorMessage(HttpStatus.NOT_FOUND.value(), "No categories found")));
		}

	}

	@GetMapping("/public/{categoryName}/getAllSubCategories")
	public ResponseEntity<CategoryResponse> getAllSubCategories(@PathVariable("categoryName") String categoryName) {

		Category category = categoryService.getAllSubCategories(categoryName);

		if (!CollectionUtils.isEmpty(category.getSubCategories())) {
			return ResponseEntity.ok().body(new CategoryResponse(category));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(
					new CategoryResponse(new ErrorMessage(HttpStatus.NOT_FOUND.value(), "No subCategories found")));
		}
	}

}