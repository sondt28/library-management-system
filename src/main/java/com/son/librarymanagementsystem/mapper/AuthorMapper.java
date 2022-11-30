package com.son.librarymanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.son.librarymanagementsystem.dto.AuthorDto;
import com.son.librarymanagementsystem.model.Author;

@Mapper
public interface AuthorMapper {
	
	AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);
	
	AuthorDto toDto(Author model);
	Author toModel(AuthorDto dto);
}
