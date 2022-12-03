package com.son.librarymanagementsystem.dto;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
	
	private int id;
	
	private String title;
	
	private Date publicationDate;
	
	@Min(value = 1, message = "{book.copiesOwned.min}")
	private int copiesOwned;
	
	private Set<AuthorDto> authors;	
}
