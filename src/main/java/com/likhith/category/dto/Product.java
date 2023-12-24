package com.likhith.category.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {

	private String name;
	private String brand;
	private String description;
	private Price price;
	private Inventory inventory;
	private List<Attributes> attributes;

}