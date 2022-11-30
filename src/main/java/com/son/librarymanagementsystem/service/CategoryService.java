package com.son.librarymanagementsystem.service;

import java.util.List;

import com.son.librarymanagementsystem.dto.CategoryWithBooksDto;
import com.son.librarymanagementsystem.dto.CategoryDto;

public interface CategoryService {
	CategoryDto saveCategory(CategoryDto dto);
	
	List<CategoryDto> findAllCategory();
	
	CategoryWithBooksDto findById(int id);
	
	CategoryWithBooksDto addBookIntoCategory(int categoryId, int bookId);
}
