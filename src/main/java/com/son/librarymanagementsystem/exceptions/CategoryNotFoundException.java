package com.son.librarymanagementsystem.exceptions;

public class CategoryNotFoundException extends RuntimeException {

	public CategoryNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CategoryNotFoundException(String message) {
		super(message);
	}

	public CategoryNotFoundException(Throwable cause) {
		super(cause);
	}

	
}
