package com.son.librarymanagementsystem.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.son.librarymanagementsystem.dto.BookDto;
import com.son.librarymanagementsystem.model.Book;
import com.son.librarymanagementsystem.model.Category;
import com.son.librarymanagementsystem.repository.BookRepository;
import com.son.librarymanagementsystem.repository.CategoryRepository;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

	@Mock
	private BookRepository repository;

	@Mock
	private CategoryRepository categoryRepostory;
	
	@InjectMocks
	private BookServiceImpl service;

	private Book book;
	
	private Category category;
	
	private Pageable pageable;
	
	@BeforeEach
	void beforeEach() {
		book = new Book();
		book.setTitle("One Piece");
		
		category = new Category();
		category.setId(1);
		category.setCategoryName("Manga");
		category.addBook(book);
		
		pageable = PageRequest.of(1, 4);
	}
	
	@Test
	void shouldReturnNullBookPageByGivenTitle() {	
		
		when(repository.findByTitle("Vanga Bond", pageable)).thenReturn(null);
		
		Page<Book> actual = service.findByTitle("Vanga Bond", 1, 4);
		
		assertNull(actual);
	}
	
	@Test
	void shouldReturnPageBookByGivenCategory() {
		Page<Book> bookPage = Mockito.mock(Page.class);
		
		when(categoryRepostory.findById(1)).thenReturn(Optional.of(category));
		when(repository.findByCategory(category, pageable)).thenReturn(bookPage);
		
		Page<BookDto> bookDtos = service.findByCategory(1, 1, 3);
		
		assertNotNull(bookDtos);
	}
}

