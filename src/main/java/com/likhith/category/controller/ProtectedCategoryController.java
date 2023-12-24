package com.likhith.category.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}