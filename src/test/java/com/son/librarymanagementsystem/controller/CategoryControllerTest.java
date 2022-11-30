package com.son.librarymanagementsystem.controller;

import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.son.librarymanagementsystem.dto.CategoryDto;
import com.son.librarymanagementsystem.model.Category;
import com.son.librarymanagementsystem.repository.CategoryRepository;
import com.son.librarymanagementsystem.service.CategoryService;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private CategoryService service;
	
	@MockBean
	private CategoryRepository repository;
	
	@BeforeEach
	void beforeEach() {

	}
	
	@Test
	void whenValidCategoryThenReturn201() throws Exception {
		CategoryDto category = new CategoryDto();
		category.setCategoryName("One shot");
		
		when(service.saveCategory(any(CategoryDto.class))).thenReturn(category);
		
		RequestBuilder request = MockMvcRequestBuilders
								.post("/api/v1/categories")
//								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON)
								.content(objectMapper.writeValueAsString(category));
		
		mockMvc.perform(request)
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.categoryName", is(category.getCategoryName())));
	
		verify(service).saveCategory(any(CategoryDto.class));
		verify(repository).findByCategoryName(any());
	}
	
	@Test
	void whenDuplicateCategoryNameThenReturn400() throws Exception {
		Category saveCategory = new Category();
		saveCategory.setCategoryName("Comic");
		
		CategoryDto category = new CategoryDto();
		category.setCategoryName("Comic");
		
		when(repository.findByCategoryName("Comic")).thenReturn(Optional.of(saveCategory));
		
		RequestBuilder request = MockMvcRequestBuilders
								.post("/api/v1/categories")
								.contentType(MediaType.APPLICATION_JSON)
								.content(objectMapper.writeValueAsString(category));

		mockMvc.perform(request)
				.andExpect(status().isBadRequest());
	}
}
