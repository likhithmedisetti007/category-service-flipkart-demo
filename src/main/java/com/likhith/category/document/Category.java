package com.likhith.category.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.likhith.category.dto.SubCategory;

import lombok.Data;

@Document(collection = "category")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category {

	@Id
	@JsonIgnore
	private String id;
	private String name;
	@Transient
	private List<SubCategory> subCategoryList;
	private List<String> subCategories;
}