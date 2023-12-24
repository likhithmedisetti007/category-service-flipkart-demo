package com.likhith.category.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.likhith.category.document.Category;

@Service
public interface CategoryService {

	void loadDataFromMongoDB();

	List<String> getAllCategories();

	Category getAllSubCategories(String category);

	Category getAllProducts(String categoryName, String subCategoryName);

}