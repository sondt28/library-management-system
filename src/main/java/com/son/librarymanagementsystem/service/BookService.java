package com.son.librarymanagementsystem.service;

import org.springframework.data.domain.Page;

import com.son.librarymanagementsystem.dto.BookDto;
import com.son.librarymanagementsystem.dto.BookWithAuthorsDto;
import com.son.librarymanagementsystem.model.Book;

public interface BookService {
	BookDto saveBook(BookDto dto);
	
	BookWithAuthorsDto addAuthorToBook(int bookId, int authorId);
	
	Page<BookDto> findAllBookWithPagination(int offset, int pageSize);
	
	Page<Book> findByTitle(String title, int offset, int pageSize);
	
	Page<BookDto> findByCategory(int categoryId, int offset, int pageSize);
}
