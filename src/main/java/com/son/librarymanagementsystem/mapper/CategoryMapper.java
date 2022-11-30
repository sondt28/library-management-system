package com.son.librarymanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.son.librarymanagementsystem.dto.CategoryWithBooksDto;
import com.son.librarymanagementsystem.dto.CategoryDto;
import com.son.librarymanagementsystem.model.Category;

@Mapper
public interface CategoryMapper {
	
	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
	
	Category toModel(CategoryDto dto);
	CategoryDto toDto(Category model);
	CategoryWithBooksDto toCategoryDetailDto(Category model);
}
