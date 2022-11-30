package com.son.librarymanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.son.librarymanagementsystem.dto.BookDto;
import com.son.librarymanagementsystem.dto.BookWithAuthorsDto;
import com.son.librarymanagementsystem.model.Book;

@Mapper
public interface BookMapper {

	BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
	
	BookDto toDto(Book model);
	Book toModel(BookDto dto);
	BookWithAuthorsDto toBookWithAuthors(Book model);
}
