package com.son.librarymanagementsystem.service;

import java.util.Date;

import org.springframework.data.domain.Page;

import com.son.librarymanagementsystem.dto.BookDto;
import com.son.librarymanagementsystem.dto.BookWithAuthorsDto;

public interface BookService {
	BookDto saveBook(BookDto dto);
	
	BookWithAuthorsDto addAuthorToBook(int bookId, int authorId);
	
	Page<BookDto> findAllBookWithPagination(int offset, int pageSize);
	
	Page<BookDto> findByTitle(String title, int offset, int pageSize);
	
	Page<BookDto> findByPublicationDate(Date publicationDate, int offset, int pageSize);
	
	Page<BookDto> findByCategory(int categoryId, int offset, int pageSize);
	
	Page<BookDto> findByAuthor(String firstName, String lastName, int offset, int pageSize);
}
