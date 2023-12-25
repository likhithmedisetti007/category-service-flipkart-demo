package com.likhith.category.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.likhith.category.client.ElectronicsClient;
import com.likhith.category.client.FashionClient;
import com.likhith.category.document.Category;
import com.likhith.category.downstream.dto.SubCategoryResponse;
import com.likhith.category.dto.Product;
import com.likhith.category.dto.SubCategory;
import com.likhith.category.exception.ValidationException;
import com.likhith.category.mapper.CategoryMapper;
import com.likhith.category.repository.CategoryRepository;

import jakarta.annotation.PostConstruct;

@Component
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	FashionClient fashionClient;

	@Autowired
	ElectronicsClient electronicsClient;

	@Autowired
	CategoryMapper categoryMapper;

	List<Category> categoryList;

	@PostConstruct
	@Override
	public void loadDataFromMongoDB() {
		categoryList = categoryRepository.findAll();
	}

	@Override
	public List<String> getAllCategories() {

		if (CollectionUtils.isEmpty(categoryList)) {
			throw new ValidationException(HttpStatus.NOT_FOUND.value(), "Categories not loaded");
		}

		return categoryList.stream().map(category -> category.getName()).collect(Collectors.toList());
	}

	@Override
	public Category getAllSubCategories(String categoryName) {

		if (CollectionUtils.isEmpty(categoryList)) {
			throw new ValidationException(HttpStatus.NOT_FOUND.value(), "Categories not loaded");
		}

		Category category = categoryList.stream()
				.filter(categories -> categoryName.equalsIgnoreCase(categories.getName())).findFirst()
				.orElseThrow(() -> new ValidationException(HttpStatus.NOT_FOUND.value(),
						"No such category found in categoryList"));

		return category;
	}

	@Override
	public Category getAllProducts(String categoryName, String subCategoryName, boolean availability, double minPrice,
			double maxPrice) {

		Category response = new Category();
		SubCategoryResponse subCategoryResponse = null;

		if (CollectionUtils.isEmpty(categoryList)) {
			throw new ValidationException(HttpStatus.NOT_FOUND.value(), "Categories not loaded");
		}

		Category category = categoryList.stream()
				.filter(categories -> categoryName.equalsIgnoreCase(categories.getName())).findFirst()
				.orElseThrow(() -> new ValidationException(HttpStatus.NOT_FOUND.value(),
						"No such category found in categoryList"));

		category.getSubCategories().stream().filter(subCategory -> subCategoryName.equalsIgnoreCase(subCategory))
				.findFirst().orElseThrow(() -> new ValidationException(HttpStatus.NOT_FOUND.value(),
						"No such subCategory found in categoryList"));

		switch (categoryName.toLowerCase()) {
		case "fashion":
			subCategoryResponse = fashionClient.getAllProducts(subCategoryName, availability, minPrice, maxPrice);
			break;
		case "electronics":
			subCategoryResponse = electronicsClient.getAllProducts(subCategoryName, availability, minPrice, maxPrice);
			break;
		default:
			throw new ValidationException(HttpStatus.NOT_IMPLEMENTED.value(),
					"Unsupported subCategory: " + subCategoryName);
		}

		SubCategory subCategory = categoryMapper.mapToSubCategory(subCategoryResponse);

		response.setName(categoryName);
		response.setSubCategoryList(Arrays.asList(subCategory));

		return response;
	}

	@Override
	public String addProduct(String categoryName, String subCategoryName, Product product) {

		String message = null;

		switch (categoryName.toLowerCase()) {
		case "fashion":
			message = fashionClient.addProduct(subCategoryName, product);
			break;
		case "electronics":
			message = electronicsClient.addProduct(subCategoryName, product);
			break;
		default:
			throw new ValidationException(HttpStatus.NOT_IMPLEMENTED.value(),
					"Unsupported subCategory: " + subCategoryName);
		}
		return message;
	}

	@Override
	public String updateProduct(String categoryName, String subCategoryName, Product product) {

		String message = null;

		switch (categoryName.toLowerCase()) {
		case "fashion":
			message = fashionClient.updateProduct(subCategoryName, product);
			break;
		case "electronics":
			message = electronicsClient.updateProduct(subCategoryName, product);
			break;
		default:
			throw new ValidationException(HttpStatus.NOT_IMPLEMENTED.value(),
					"Unsupported subCategory: " + subCategoryName);
		}
		return message;
	}

	@Override
	public String deleteProduct(String categoryName, String subCategoryName, Product product) {

		String message = null;

		switch (categoryName.toLowerCase()) {
		case "fashion":
			message = fashionClient.deleteProduct(subCategoryName, product);
			break;
		case "electronics":
			message = electronicsClient.deleteProduct(subCategoryName, product);
			break;
		default:
			throw new ValidationException(HttpStatus.NOT_IMPLEMENTED.value(),
					"Unsupported subCategory: " + subCategoryName);
		}
		return message;
	}

}