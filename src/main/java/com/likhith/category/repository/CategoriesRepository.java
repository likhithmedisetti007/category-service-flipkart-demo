package com.likhith.category.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.likhith.category.document.Categories;

public interface CategoriesRepository extends MongoRepository<Categories, String> {

}