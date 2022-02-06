package com.qa.nutritionapp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.qa.nutritionapp.data.FoodEntity;
import com.qa.nutritionapp.data.FoodRepository;
import com.qa.nutritionapp.service.FoodService;

@ExtendWith(MockitoExtension.class)
public class FoodServiceTest {
	
	@Mock
	private FoodRepository repository;
	
	@InjectMocks
	private FoodService service;
	
	@Test
	public void testGetAllFoods() {
		ArrayList<FoodEntity> foods = new ArrayList<FoodEntity>();
		FoodEntity cacao = new FoodEntity(Long.valueOf(1), "cacao", (float) 20.00, (float) 12.45, (float) 9.30);
		FoodEntity rice = new FoodEntity(Long.valueOf(2), "rice", (float) 15.00, (float) 7.45, (float) 8.30);
		foods.addAll(List.of(cacao, rice));
		when(this.repository.findAll()).thenReturn(foods);
		assertThat(this.service.getAllFoods()).isEqualTo(foods);
	}
	
	@Test
	public void testGetFoodByID() {
		Long id = Long.valueOf(1);
		FoodEntity cacao = new FoodEntity(id, "cacao", (float) 20.00, (float) 12.45, (float) 9.30);
		when(this.repository.findById(id)).thenReturn(Optional.of(cacao));
		assertThat(this.service.getFoodByID(id)).isEqualTo(cacao);
	}
	
	@Test
	public void testGetFoodByName() {
		ArrayList<FoodEntity> foods = new ArrayList<FoodEntity>();
		Long id = Long.valueOf(1);
		FoodEntity cacao = new FoodEntity(id, "cacao", (float) 20.00, (float) 12.45, (float) 9.30);
		foods.addAll(List.of(cacao));
		when(this.repository.findFoodEntityByName("cacao")).thenReturn(foods);
		assertThat(this.service.getFoodByName("cacao")).isEqualTo(foods);
	}
	
	@Test
	public void testCreateFood() {
		Long id = Long.valueOf(1);
		FoodEntity cacao = new FoodEntity(id, "cacao", (float) 20.00, (float) 12.45, (float) 9.30);
		when(this.repository.save(cacao)).thenReturn(cacao);
		assertThat(this.service.createFood(cacao)).isEqualTo(cacao);
	}
	
	@Test
	public void testUpdateFood() {
		Long id = Long.valueOf(1);
		FoodEntity cacao = new FoodEntity(id, "cacao", (float) 20.00, (float) 12.45, (float) 9.30);
		when(this.repository.findById(id)).thenReturn(Optional.of(cacao));
		assertThat(service.updateFood(cacao, id)).isEqualTo(cacao);
	}
}
