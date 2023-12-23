package com.likhith.category.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Inventory {

	private int total;
	private int available;
	private int reserved;

}