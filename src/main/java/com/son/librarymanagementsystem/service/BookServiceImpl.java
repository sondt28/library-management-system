package com.son.librarymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.son.librarymanagementsystem.dto.BookDto;
import com.son.librarymanagementsystem.dto.BookWithAuthorsDto;
import com.son.librarymanagementsystem.exceptions.AuthorNotFoundException;
import com.son.librarymanagementsystem.exceptions.BookNotFoundException;
import com.son.librarymanagementsystem.exceptions.CategoryNotFoundException;
import com.son.librarymanagementsystem.mapper.BookMapper;
import com.son.librarymanagementsystem.model.Author;
import com.son.librarymanagementsystem.model.Book;
import com.son.librarymanagementsystem.model.Category;
import com.son.librarymanagementsystem.repository.AuthorRepository;
import com.son.librarymanagementsystem.repository.BookRepository;
import com.son.librarymanagementsystem.repository.CategoryRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository repository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public BookDto saveBook(BookDto dto) {
		
		Book book = BookMapper.INSTANCE.toModel(dto);
		
		return BookMapper.INSTANCE.toDto(repository.save(book));
	}

	@Override
	public BookWithAuthorsDto addAuthorToBook(int bookId, int authorId) {
		
		Book book = repository.findById(bookId)
				.orElseThrow(() -> new BookNotFoundException("Book not found."));
		
		Author author = authorRepository.findById(authorId)
				.orElseThrow(() -> new AuthorNotFoundException("Author not found."));
		
		book.addAuthor(author);
		
		book = repository.save(book);
		
		BookWithAuthorsDto dto = BookMapper.INSTANCE.toBookWithAuthors(book);
		
		return dto;
	}

	@Override
	public Page<BookDto> findAllBookWithPagination(int offset, int pageSize) {
		
		Page<Book> books = repository.findAll(PageRequest.of(offset, pageSize));
		
		Page<BookDto> bookDtos = books.map(t -> BookMapper.INSTANCE.toDto(t));
		
		return bookDtos;
	}

	@Override
	public Page<Book> findByTitle(String title, int offset, int pageSize) {
		
		Pageable pageable = PageRequest.of(offset, pageSize);
		
		Page<Book> book = repository.findByTitle(title, pageable);
		
		return book;
	}

	@Override
	public Page<BookDto> findByCategory(int categoryId, int offset, int pageSize) {
		
		Category category = categoryRepository.findById(categoryId)
					.orElseThrow(() -> new CategoryNotFoundException("Category not found!"));
		
		Pageable pageable = PageRequest.of(offset - 1, pageSize);
		
		Page<Book> books = repository.findByCategory(category, pageable);
		
		Page<BookDto> bookDtos = books.map(t -> BookMapper.INSTANCE.toDto(t));
		
		return bookDtos;
	}
	
}
