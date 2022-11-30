package com.son.librarymanagementsystem.dto;

import java.util.Date;

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
	
	private int copiesOwned;
		
}
