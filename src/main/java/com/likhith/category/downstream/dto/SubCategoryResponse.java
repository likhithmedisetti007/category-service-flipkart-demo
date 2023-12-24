package com.likhith.category.downstream.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.likhith.category.dto.Product;
import com.likhith.category.exception.ErrorMessage;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubCategoryResponse {

	private String name;
	private List<Product> products;

	private ErrorMessage message;

}