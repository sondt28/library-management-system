package com.son.librarymanagementsystem.controller;

import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.son.librarymanagementsystem.dto.AuthorDto;
import com.son.librarymanagementsystem.service.AuthorService;

@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private AuthorService service;
	
	@Test
	void shouldReturnAuthorDtoAndStatus201() throws Exception {
		AuthorDto authorDto = new AuthorDto();
		authorDto.setFirstName("Thu Giang");
		authorDto.setLastName("Nguyen Duy Can");
		
		when(service.saveAuthor(any(AuthorDto.class))).thenReturn(authorDto);
		
		RequestBuilder request = MockMvcRequestBuilders
								.post("/api/v1/authors")
								.contentType(MediaType.APPLICATION_JSON)
								.content(objectMapper.writeValueAsString(authorDto));
		
		mockMvc.perform(request)
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.firstName", is("Thu Giang")))
				.andExpect(jsonPath("$.lastName", is("Nguyen Duy Can")));
	}
}
