package com.son.librarymanagementsystem.dto;

import com.son.librarymanagementsystem.validation.annotation.UniqueCategory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
	
	private int id;
	
	@UniqueCategory
	private String categoryName;
}
