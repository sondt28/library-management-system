package com.son.librarymanagementsystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.son.librarymanagementsystem.dto.CategoryDto;
import com.son.librarymanagementsystem.dto.CategoryWithBooksDto;
import com.son.librarymanagementsystem.service.CategoryService;
import com.son.librarymanagementsystem.utils.ErrorResponse;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

	@Autowired
	private CategoryService service;
	
	@GetMapping
	public Object findAllCategory() {
		List<CategoryDto> dtos = service.findAllCategory();
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("{categoryId}")
	public Object findCategoryById(@PathVariable("categoryId") Integer categoryId) {
		CategoryWithBooksDto dtos = service.findById(categoryId);
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@PostMapping
	public Object saveCategory(@Valid @RequestBody CategoryDto dto, BindingResult errors) {
		
		if (errors.hasErrors())
			return new ResponseEntity<>(ErrorResponse.getErrorResponse(errors), 
										HttpStatus.BAD_REQUEST);
		
		CategoryDto newCategory = service.saveCategory(dto);
		
		return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
	}
	
	@PutMapping("{categoryId}/{bookId}")
	public Object addBookIntoCategory(@PathVariable("categoryId") Integer categoryId,
									  @PathVariable("bookId") Integer bookId) {
		
		CategoryWithBooksDto dto = service.addBookIntoCategory(categoryId, bookId);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
}
