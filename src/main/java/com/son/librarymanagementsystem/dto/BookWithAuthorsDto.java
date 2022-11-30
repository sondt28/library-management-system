package com.son.librarymanagementsystem.dto;

import java.util.Date;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookWithAuthorsDto {
	
	private int id;
	
	private String title;
	
	private Date publicationDate;
	
	private int copiesOwned;
	
	private Set<AuthorDto> authorDtos;
}
