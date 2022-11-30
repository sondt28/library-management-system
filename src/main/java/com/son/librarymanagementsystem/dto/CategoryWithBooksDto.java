package com.son.librarymanagementsystem.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryWithBooksDto {

	private int id;
	
	private String categoryName;
	
	private Set<BookDto> books;
}
