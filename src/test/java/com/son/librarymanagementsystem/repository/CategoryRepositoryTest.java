package com.son.librarymanagementsystem.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.son.librarymanagementsystem.model.Book;
import com.son.librarymanagementsystem.model.Category;

@DataJpaTest
public class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository repository;
	
	private Category category;
	
	private Book book;
	
	@BeforeEach
	void beforeEach() {
		category = new Category();
		category.setCategoryName("Manga");
		
		book = new Book();
		book.setTitle("One Piece");
	}
	
	@Test
	void saveCategory() {
		Category save = repository.save(category);
		
		assertEquals("Manga", save.getCategoryName());
	}
	
	@Test
	void saveCategoryWithBook() {
		category.addBook(book);
		Category save = repository.save(category);
		
		assertEquals("Manga", save.getCategoryName());
		assertNotNull(save.getBooks());
		assertEquals(1, save.getBooks().size());
	}
	
	@Test
	void findAllCategoryEmpty() {
		List<Category> categories = repository.findAll();
		
		assertEquals(0, categories.size());
	}
	
	@Test
	void findAllCategoryHasTwoRecord() {
		Category category1 = new Category();
		category1.setCategoryName("Magic");
	
		Category category2 = new Category();
		category2.setCategoryName("One shot");
		
		repository.save(category1);
		repository.save(category2);
		
		List<Category> categories = repository.findAll();
		
		assertEquals(2, categories.size());
	}
	
	@Test
	void findByCategoryName() {
		Category category1 = new Category();
		category1.setCategoryName("Magic");
		repository.save(category1);
		
		Optional<Category> actual = repository.findByCategoryName("Magic");
		
		assertNotNull(actual);
		assertEquals("Magic", actual.get().getCategoryName());
	}
}
