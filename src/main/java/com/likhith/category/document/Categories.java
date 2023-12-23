package com.likhith.category.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "categories")
@Data
public class Categories {

	@Id
	private String id;
	private String name;
}