package com.likhith.category.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.likhith.category.document.Category;
import com.likhith.category.exception.ValidationException;
import com.likhith.category.repository.CategoryRepository;

import jakarta.annotation.PostConstruct;

@Component
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

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
	public Category getAllSubCategories(String category) {

		if (CollectionUtils.isEmpty(categoryList)) {
			throw new ValidationException(HttpStatus.NOT_FOUND.value(), "Categories not loaded");
		}

		Optional<Category> optionalCategory = categoryList.stream()
				.filter(categories -> category.equalsIgnoreCase(categories.getName())).findFirst();

		if (optionalCategory.isEmpty()) {
			throw new ValidationException(HttpStatus.NOT_FOUND.value(), "No such category found");
		}

		return optionalCategory.get();
	}

}