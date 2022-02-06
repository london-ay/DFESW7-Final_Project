package com.qa.nutritionapp.controller;

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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.nutritionapp.data.FoodEntity;
import com.qa.nutritionapp.data.FoodRepository;

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
	
	private Long nextNewElementsId;
	
	@BeforeEach
	public void init() {
		Long id1 = Long.valueOf(1);
		Long id2 = Long.valueOf(2);
		List<FoodEntity> foods = List.of(new FoodEntity(id1, "cacao", (float) 10.00, (float) 20.00, (float) 30.50), new FoodEntity(id2, "rice", (float) 5.00, (float) 10.50, (float) 15.00));
		foodDatabase = new ArrayList<>();
		foodDatabase.addAll(repository.saveAll(foods));
		int size = foodDatabase.size();
		nextNewElementsId = foodDatabase.get(size - 1).getId() + 1;
	}
	
	@Test
	public void testGetAllFoods() throws Exception {
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/food");
		mockRequest.accept(MediaType.APPLICATION_JSON);
		List<FoodEntity> expectedFoods = repository.findAll();
		String foods = objectMapper.writeValueAsString(expectedFoods);
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(foods);
		mockMvc.perform(mockRequest).andExpect(contentMatcher);
	}
	
	@Test
	public void testCreateFood() throws Exception {
		Long id = Long.valueOf(1);
		FoodEntity newFood = new FoodEntity(id, "cacao", (float) 10.00, (float) 20.00, (float) 30.50);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/food");
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		mockRequest.content(objectMapper.writeValueAsString(newFood));
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		FoodEntity expectedFood = new FoodEntity(nextNewElementsId, "cacao", (float) 10.0, (float) 20.0, (float) 30.5);
		
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expectedFood));
		
		//ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expectedFood));
		System.out.println(objectMapper.writeValueAsString(expectedFood));
		mockMvc.perform(mockRequest).andExpect(contentMatcher);
	}
	
	
}
