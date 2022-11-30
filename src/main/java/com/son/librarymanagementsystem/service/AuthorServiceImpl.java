package com.son.librarymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.librarymanagementsystem.dto.AuthorDto;
import com.son.librarymanagementsystem.mapper.AuthorMapper;
import com.son.librarymanagementsystem.model.Author;
import com.son.librarymanagementsystem.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository repository;
	
	@Override
	public AuthorDto saveAuthor(AuthorDto dto) {
		
		Author author = AuthorMapper.INSTANCE.toModel(dto);
		
		author = repository.save(author);
		
		return AuthorMapper.INSTANCE.toDto(author);
	}
}
