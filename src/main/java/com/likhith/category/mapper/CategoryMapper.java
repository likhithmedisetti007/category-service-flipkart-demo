package com.likhith.category.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import com.likhith.category.downstream.dto.SubCategoryResponse;
import com.likhith.category.dto.SubCategory;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

	SubCategory mapToSubCategory(SubCategoryResponse subCategoryResponse);

}