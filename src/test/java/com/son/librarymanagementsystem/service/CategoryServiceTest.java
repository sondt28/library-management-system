package com.son.librarymanagementsystem.service;

import static org.mockito.Mockito.when;

import java.util.List;

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
	
	@Test
	void shouldReturnListCategoryDtoWithTwoRecord() {
		Category category1 = new Category();
		category1.setCategoryName("One shot");
		repository.save(category1);
		
		Category category2 = new Category();
		category2.setCategoryName("Manga");
		repository.save(category2);
		
		CategoryDto categoryDto1 = new CategoryDto();
		categoryDto1.setCategoryName("One shot");
		
		CategoryDto categoryDto2 = new CategoryDto();
		categoryDto2.setCategoryName("Manga");
		
		when(repository.findAll()).thenReturn(List.of(category1, category2));
		
		List<CategoryDto> actual = service.findAllCategory();
		
		assertEquals(2, actual.size());
		assertEquals("One shot", actual.get(0).getCategoryName());
	}
}
