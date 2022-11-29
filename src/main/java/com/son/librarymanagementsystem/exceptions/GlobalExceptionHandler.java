package com.son.librarymanagementsystem.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public Object handleGlobalException(Exception exc) {
		Map<String, Object> map = new HashMap<>();
		map.put("timeStamp", LocalDateTime.now());
		map.put("error", exc.getMessage());
		map.put("status", HttpStatus.BAD_REQUEST.value());
		
		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
	}
}
