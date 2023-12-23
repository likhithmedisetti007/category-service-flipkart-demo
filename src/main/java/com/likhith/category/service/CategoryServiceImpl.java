package com.likhith.category.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.likhith.category.document.Categories;
import com.likhith.category.repository.CategoriesRepository;

import jakarta.annotation.PostConstruct;

@Component
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoriesRepository categoriesRepository;

	List<String> categories;

	@PostConstruct
	public void loadDataFromMongoDB() {
		List<Categories> categoriesList = categoriesRepository.findAll();

		if (!CollectionUtils.isEmpty(categoriesList)) {
			categories = categoriesList.stream().map(category -> category.getName()).collect(Collectors.toList());
		}
	}

	@Override
	public List<String> getAllCategories() {

		if (!CollectionUtils.isEmpty(categories)) {
			return categories;
		}
		return null;
	}

}