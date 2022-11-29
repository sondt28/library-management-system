package com.son.librarymanagementsystem.service;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.son.librarymanagementsystem.dto.CategoryDto;
import com.son.librarymanagementsystem.model.Category;
import com.son.librarymanagementsystem.repository.CategoryRepository;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

	@Mock
	private CategoryRepository repository;
	
	@InjectMocks
	private CategoryServiceImpl service;
	
	@Test
	void saveCategory() {
		Category category = new Category();
		category.setCategoryName("Manga");
		
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setCategoryName("Manga");
		
		when(repository.save(any(Category.class))).thenReturn(category);
		
		CategoryDto dto = service.saveCategory(categoryDto);
		
		assertEquals("Manga", dto.getCategoryName());
		
		verify(repository, times(1)).save(any(Category.class));
	}
}
