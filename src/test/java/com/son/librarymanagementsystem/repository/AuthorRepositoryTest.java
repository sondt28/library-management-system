package com.son.librarymanagementsystem.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.son.librarymanagementsystem.exceptions.AuthorNotFoundException;
import com.son.librarymanagementsystem.model.Author;

@DataJpaTest
public class AuthorRepositoryTest {

	@Autowired
	private AuthorRepository repository;
	
	@Test
	void shouldReturnAuthorByGivenName() {
		Author author = new Author();
		author.setFirstName("Oda");
		author.setLastName("Shin");
		
		repository.save(author);
		
		Author actual = repository.findByFirstNameAndLastName("Oda", "Shin")
						.orElseThrow(() -> new AuthorNotFoundException("Author not found!"));
		
		assertEquals("Oda", actual.getFirstName());
		assertEquals("Shin", actual.getLastName());
	}
}
