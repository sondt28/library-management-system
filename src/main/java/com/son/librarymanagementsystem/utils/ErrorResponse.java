package com.son.librarymanagementsystem.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;

public class ErrorResponse {

	public static List<String> getErrorResponse(BindingResult errors) {
		return errors.getAllErrors()
					.stream()
					.map(t -> t.getDefaultMessage())
					.collect(Collectors.toList());
	}
}
