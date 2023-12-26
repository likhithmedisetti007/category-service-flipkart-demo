package com.likhith.category.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.likhith.category.downstream.dto.SubCategoryResponse;
import com.likhith.category.dto.Product;

@FeignClient(name = "electronics-service-flipkart-demo", path = "/electronics")
public interface ElectronicsClient {

	@GetMapping("/{subCategoryName}/getAllProducts")
	SubCategoryResponse getAllProducts(@PathVariable("subCategoryName") String subCategoryName,
			@RequestParam(name = "availability") boolean availability, @RequestParam(name = "minPrice") double minPrice,
			@RequestParam(name = "maxPrice") double maxPrice);

	@PostMapping("/{subCategoryName}/addProduct")
	String addProduct(@PathVariable("subCategoryName") String subCategoryName, @RequestBody Product product);

	@PutMapping("/{subCategoryName}/updateProduct")
	String updateProduct(@PathVariable("subCategoryName") String subCategoryName, @RequestBody Product product);

	@DeleteMapping("/{subCategoryName}/deleteProduct")
	String deleteProduct(@PathVariable("subCategoryName") String subCategoryName, @RequestBody Product product);

}