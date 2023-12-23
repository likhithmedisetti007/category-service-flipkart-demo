package com.likhith.category.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.likhith.category.exception.ErrorMessage;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryResponse {

	private List<String> categories;

	private String name;
	private String brand;
	private String description;
	private Price price;
	private Inventory inventory;
	private List<Attributes> attributes;

	private ErrorMessage message;

	public CategoryResponse(List<String> categories) {
		super();
		this.categories = categories;
	}

	public CategoryResponse(ErrorMessage message) {
		super();
		this.message = message;
	}

}