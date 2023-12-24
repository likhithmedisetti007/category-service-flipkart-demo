package com.likhith.category.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.likhith.category.document.Category;
import com.likhith.category.exception.ErrorMessage;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryResponse {

	private Category category;
	private List<String> categoriesList;

	private ErrorMessage message;

	public CategoryResponse(Category category) {
		super();
		this.category = category;
	}

	public CategoryResponse(List<String> categoriesList) {
		super();
		this.categoriesList = categoriesList;
	}

	public CategoryResponse(ErrorMessage message) {
		super();
		this.message = message;
	}

}