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
		Pageable pageable = PageRequest.of(1, 3);
		Page<Book> page = repository.findByCategory(category, pageable);
		
		assertNotNull(page);
		assertThat(page.getTotalPages()).isEqualTo(1);
	}
}
