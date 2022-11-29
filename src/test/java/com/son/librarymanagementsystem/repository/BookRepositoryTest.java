package com.son.librarymanagementsystem.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.son.librarymanagementsystem.model.Book;

@DataJpaTest
public class BookRepositoryTest {

	@Autowired
	private BookRepository repository;

	@Test
	void saveBook() {
		Book book = new Book();
		book.setTitle("One Piece");
		
		Book save = repository.save(book);
		
		assertEquals("One Piece", save.getTitle());
	}
}
