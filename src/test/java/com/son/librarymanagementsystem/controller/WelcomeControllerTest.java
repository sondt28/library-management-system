package com.son.librarymanagementsystem.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(WelcomeController.class)
public class WelcomeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void welcome() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
								.get("/welcome")
								.with(csrf())
								.with(user("admin").password("1234"))
								.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
							.andExpect(status().is(200))
							.andReturn();
		
		assertEquals("Welcome to Library Management system !", result.getResponse().getContentAsString());
	}
}
