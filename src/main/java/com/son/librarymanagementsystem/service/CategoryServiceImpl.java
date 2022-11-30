package com.son.librarymanagementsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.librarymanagementsystem.dto.CategoryWithBooksDto;
import com.son.librarymanagementsystem.dto.CategoryDto;
import com.son.librarymanagementsystem.exceptions.BookNotFoundException;
import com.son.librarymanagementsystem.exceptions.CategoryNotFoundException;
import com.son.librarymanagementsystem.mapper.CategoryMapper;
import com.son.librarymanagementsystem.model.Book;
import com.son.librarymanagementsystem.model.Category;
import com.son.librarymanagementsystem.repository.BookRepository;
import com.son.librarymanagementsystem.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public List<CategoryDto> findAllCategory() {
		
		List<Category> categories = repository.findAll();
		
		List<CategoryDto> categoriesDto = categories.stream()
										.map(t -> CategoryMapper.INSTANCE.toDto(t))
										.collect(Collectors.toList());
		
		return categoriesDto;
	}
	
	@Override
	public CategoryDto saveCategory(CategoryDto dto) {
		Category category = CategoryMapper.INSTANCE.toModel(dto);
		
		repository.save(category);
		
		CategoryDto newDto = CategoryMapper.INSTANCE.toDto(category);
		
		return newDto;
	}

	@Override
	public CategoryWithBooksDto findById(int id) {
		Category category = repository.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException("Category not found."));
		
		CategoryWithBooksDto dto = CategoryMapper.INSTANCE.toCategoryDetailDto(category);
		
		return dto;
	}
	
	@Override
	public CategoryWithBooksDto addBookIntoCategory(int categoryId, int bookId) {
		
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new BookNotFoundException("Book not found."));
		
		Category category = repository.findById(categoryId)
				.orElseThrow(() -> new BookNotFoundException("Category not found."));
		
		category.addBook(book);
		
		category = repository.save(category);
		
		CategoryWithBooksDto dto = CategoryMapper.INSTANCE.toCategoryDetailDto(category);
		
		return dto;
	}
}
