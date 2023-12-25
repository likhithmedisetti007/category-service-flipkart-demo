package com.likhith.category.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.likhith.category.document.Category;
import com.likhith.category.dto.Product;

@Service
public interface CategoryService {

	void loadDataFromMongoDB();

	List<String> getAllCategories();

	Category getAllSubCategories(String category);

	Category getAllProducts(String categoryName, String subCategoryName, boolean availability, double minPrice,
			double maxPrice);

	String addProduct(String categoryName, String subCategoryName, Product product);

	String updateProduct(String categoryName, String subCategoryName, Product product);

	String deleteProduct(String categoryName, String subCategoryName, Product product);

}