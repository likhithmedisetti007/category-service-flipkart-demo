package com.likhith.category.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.likhith.category.downstream.dto.SubCategoryResponse;

@FeignClient(name = "fashion-service-flipkart-demo", path = "/fashion")
public interface FashionClient {

	@GetMapping("/{subCategoryName}/getAllProducts")
	SubCategoryResponse getAllProducts(@PathVariable("subCategoryName") String subCategoryName);

}