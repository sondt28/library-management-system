package com.son.librarymanagementsystem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.son.librarymanagementsystem.dto.CategoryDto;
import com.son.librarymanagementsystem.service.CategoryService;
import com.son.librarymanagementsystem.utils.ErrorResponse;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

	@Autowired
	private CategoryService service;
	
	@PostMapping
	public Object saveCategory(@Valid @RequestBody CategoryDto dto, BindingResult errors) {
		
		if (errors.hasErrors())
			return new ResponseEntity<>(ErrorResponse.getErrorResponse(errors), 
										HttpStatus.BAD_REQUEST);
		
		CategoryDto newCategory = service.saveCategory(dto);
		
		return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
	}
}
