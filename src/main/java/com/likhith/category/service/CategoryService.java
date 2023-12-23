package com.likhith.category.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CategoryService {

	List<String> getAllCategories();

}