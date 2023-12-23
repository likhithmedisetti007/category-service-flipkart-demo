package com.likhith.category.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Document(collection = "category")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category {

	@Id
	@JsonIgnore
	private String id;
	private String name;
	private List<String> subCategories;
}