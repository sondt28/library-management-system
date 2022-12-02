package com.son.librarymanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.son.librarymanagementsystem.dto.CategoryWithBooksDto;
import com.son.librarymanagementsystem.dto.CategoryDto;
import com.son.librarymanagementsystem.model.Category;

@Mapper
public interface CategoryMapper {
	
	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
	
	CategoryDto toDto(Category model);
	Category toModel(CategoryDto dto);
	CategoryWithBooksDto toCategoryDetailDto(Category model);
}
