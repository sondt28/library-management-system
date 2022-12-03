package com.son.librarymanagementsystem.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.son.librarymanagementsystem.dto.BookDto;
import com.son.librarymanagementsystem.exceptions.AuthorNotFoundException;
import com.son.librarymanagementsystem.model.Author;
import com.son.librarymanagementsystem.model.Book;
import com.son.librarymanagementsystem.model.Category;
import com.son.librarymanagementsystem.repository.AuthorRepository;
import com.son.librarymanagementsystem.repository.BookRepository;
import com.son.librarymanagementsystem.repository.CategoryRepository;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

	@Mock
	private BookRepository repository;

	@Mock
	private CategoryRepository categoryRepostory;
	
	@Mock
	private AuthorRepository authorRepository;
	
	@InjectMocks
	private BookServiceImpl service;

	private Book book;
	
	private Category category;
	
	private Author author;
	
	private Pageable pageable;
	
	@BeforeEach
	void beforeEach() {
		author = new Author();
		author.setFirstName("Oda");
		author.setLastName("Eiichiro");
		
		book = new Book();
		book.setTitle("One Piece");
		book.setCopiesOwned(15);
		book.addAuthor(author);
		
		category = new Category();
		category.setId(1);
		category.setCategoryName("Manga");
		category.addBook(book);
		
		pageable = PageRequest.of(0, 5);
	}
	
	@Test
	void shouldReturnBookDtoWhenSave() {
		BookDto dto = new BookDto();
		dto.setTitle("One Piece");
		dto.setCopiesOwned(15);
		
		when(repository.save(any(Book.class))).thenReturn(book);
		
		BookDto actual = service.saveBook(dto);
		
		assertEquals("One Piece", actual.getTitle());
		assertEquals(15, actual.getCopiesOwned());
	}
	
	@Test
	void shouldReturnNullBookPageByGivenTitle() {	
		
		when(repository.findByTitle("One Piece", pageable)).thenReturn(new PageImpl<>(List.of(book)));
		
		Page<BookDto> actual = service.findByTitle("One Piece", 1, 5);
		
		assertEquals(1, actual.getContent().size());
		assertEquals(1, actual.getTotalPages());
	}
	
	@Test
	void shouldReturnPageBookByGivenAuthor() {
		
		when(authorRepository.findByFirstNameAndLastName("Oda", "Eiichiro"))
							.thenReturn(Optional.of(author));
		
		when(repository.findByAuthors(author, pageable))
							.thenReturn(new PageImpl<>(List.of(book)));
		
		Page<BookDto> actual = service.findByAuthor("Oda", "Eiichiro", 1, 5);
		
		assertEquals(1, actual.getContent().size());
		assertThat(actual.getContent().get(0).getTitle()).isEqualTo("One Piece");
		assertEquals(1, actual.getTotalPages());
	}
	
	@Test
	void shouldReturnPageBookByGivenNullAuthor() {
		
		when(authorRepository.findByFirstNameAndLastName("asdfsad", "asd"))
							.thenReturn(Optional.empty());
		
		assertThrows(AuthorNotFoundException.class, 
				() -> service.findByAuthor("asdfsad", "asd", 1, 5));
	}
}

