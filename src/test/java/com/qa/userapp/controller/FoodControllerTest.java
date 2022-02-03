package com.qa.userapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.userapp.data.FoodRepository;
import com.qa.userapp.data.FoodEntity;

@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class FoodControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private FoodRepository repository;
	
	private List<FoodEntity> foodDatabase;
	
	@BeforeEach
	public void init() {
		Long id1 = Long.valueOf(1);
		Long id2 = Long.valueOf(2);
		List<FoodEntity> foods = List.of(new FoodEntity(id1, "cacao", (float) 10.00, (float) 20.00, (float) 30.50), new FoodEntity(id2, "rice", (float) 5.00, (float) 10.50, (float) 15.00));
		foodDatabase = new ArrayList<>();
		foodDatabase.addAll(repository.saveAll(foods));
	}
	
	@Test
	public void testGetAllFoods() throws Exception {
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/food");
		mockRequest.accept(MediaType.APPLICATION_JSON);
		String foods = objectMapper.writeValueAsString(foodDatabase);
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(foods);
		mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}
	
	@Test
	public void testCreateFood() throws Exception {
		Long id1 = Long.valueOf(3);
		Long id2 = Long.valueOf(3);
		FoodEntity newFood = new FoodEntity(id1, "cacao", (float) 10.00, (float) 20.00, (float) 30.50);
		FoodEntity expectedFood = new FoodEntity(id1, "cacao", (float) 10.00, (float) 20.00, (float) 30.50);
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/food");
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		mockRequest.content(objectMapper.writeValueAsString(newFood));
		mockRequest.accept(MediaType.APPLICATION_JSON);
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expectedFood));
		mockMvc.perform(mockRequest).andExpect(contentMatcher);
	}
}
