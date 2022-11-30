package com.son.librarymanagementsystem.exceptions;

public class BookNotFoundException extends RuntimeException {

	public BookNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public BookNotFoundException(String message) {
		super(message);
	}

	public BookNotFoundException(Throwable cause) {
		super(cause);
	}

	
}
