package com.son.librarymanagementsystem.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

	private int id;
	
	@NotBlank(message = "{author.firstName.notblank}")
	private String firstName;
	
	@NotBlank(message = "{author.lastName.notblank}")
	private String lastName;
}
