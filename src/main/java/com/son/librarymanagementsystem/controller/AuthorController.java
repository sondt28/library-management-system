package com.son.librarymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.son.librarymanagementsystem.dto.AuthorDto;
import com.son.librarymanagementsystem.service.AuthorService;

@RestController
@RequestMapping("api/v1/authors")
public class AuthorController {
	
	@Autowired
	private AuthorService service;
	
	@PostMapping
	public Object saveAuthor(@RequestBody AuthorDto dto) {
		AuthorDto newDto = service.saveAuthor(dto);
		
		return new ResponseEntity<>(newDto, HttpStatus.CREATED);
	}

}
