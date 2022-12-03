package com.son.librarymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.son.librarymanagementsystem.dto.BookDto;
import com.son.librarymanagementsystem.dto.BookWithAuthorsDto;
import com.son.librarymanagementsystem.service.BookService;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

	private final int PAGE_SIZE = 5;
	
	@Autowired
	private BookService service;
	
	@GetMapping("find-all/{offset}")
	public Object findAllBookWithPage(@PathVariable("offset") Integer offset) {
		
		Page<BookDto> dtoPage = service.findAllBookWithPagination(offset, PAGE_SIZE);
		
		return new ResponseEntity<>(dtoPage, HttpStatus.OK);
	}
	
	@GetMapping("find-by-category/{categoryId}/{offset}")
	public Object findBooksByCategoryId(@PathVariable("categoryId") Integer categoryId,
										@PathVariable("offset") Integer offset) {
		
		Page<BookDto> dtoPage = service.findByCategory(categoryId, offset, PAGE_SIZE);
		
		return new ResponseEntity<>(dtoPage, HttpStatus.OK);
	}
	
	@PostMapping
	public Object saveBook(@RequestBody BookDto dto) {
		BookDto newDto = service.saveBook(dto);
		
		return new ResponseEntity<>(newDto, HttpStatus.CREATED);
	}
	
	@PutMapping("{bookId}/{authorId}")
	public Object addAuthorToBook(@PathVariable("bookId") Integer bookId,
								  @PathVariable("authorId") Integer authorId) {
		
		BookWithAuthorsDto dto = service.addAuthorToBook(bookId, authorId);
		
		return dto;
	}
}
