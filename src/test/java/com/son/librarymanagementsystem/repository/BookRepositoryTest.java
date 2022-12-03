package com.son.librarymanagementsystem.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.son.librarymanagementsystem.model.Book;
import com.son.librarymanagementsystem.model.Category;

@DataJpaTest
public class BookRepositoryTest {

	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	private Pageable pageable = PageRequest.of(0, 5);
	
	private Book book;
	
	private Category category;
	
	@BeforeEach
	void beforeEach() {
		book = new Book();
		book.setTitle("One Piece");
		
		category = new Category();
		category.addBook(book);
		categoryRepository.save(category);
	}
	
	@Test
	void saveBook() {
		Book save = repository.save(book);
		
		assertEquals("One Piece", save.getTitle());
	}
	
	@Test
	void shouldReturnPageBookByCategoryId() {
		repository.save(book);
		
		Page<Book> page = repository.findByCategory(category, pageable);
		
		assertNotNull(page);
		assertThat(page.getContent().size()).isEqualTo(1);
		assertThat(page.getContent().get(0).getCategory()).isEqualTo(category);
		assertThat(page.getTotalPages()).isEqualTo(1);
	}
	
	@Test
	void shouldReturnPageBookByTitle() {
		repository.save(book);
		
		Page<Book> page = repository.findByTitle("One Piece", pageable);
		
		assertNotNull(page);
		assertThat(page.getContent().size()).isEqualTo(1);
		assertEquals("One Piece", page.getContent().get(0).getTitle());
		assertEquals(1, page.getTotalPages());
		assertEquals(5, page.getSize());
	}
}
