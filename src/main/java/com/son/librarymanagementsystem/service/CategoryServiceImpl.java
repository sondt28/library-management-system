package com.son.librarymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.librarymanagementsystem.dto.CategoryDto;
import com.son.librarymanagementsystem.mapper.CategoryMapper;
import com.son.librarymanagementsystem.model.Category;
import com.son.librarymanagementsystem.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	@Override
	public CategoryDto saveCategory(CategoryDto dto) {
		Category category = CategoryMapper.INSTANCE.toModel(dto);
		
		repository.save(category);
		
		CategoryDto newDto = CategoryMapper.INSTANCE.toDto(category);
		
		return newDto;
	}
}
