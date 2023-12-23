package com.likhith.category.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.likhith.category.document.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {

}