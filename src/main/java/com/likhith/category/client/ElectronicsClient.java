package com.likhith.category.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.likhith.category.downstream.dto.SubCategoryResponse;

@FeignClient(name = "electronics-service-flipkart-demo", path = "/electronics")
public interface ElectronicsClient {

	@GetMapping("/{subCategoryName}/getAllProducts")
	SubCategoryResponse getAllProducts(String subCategoryName);

}