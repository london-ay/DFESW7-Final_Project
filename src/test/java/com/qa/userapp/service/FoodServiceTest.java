package com.qa.userapp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.qa.userapp.data.FoodRepository;
import com.qa.userapp.data.FoodEntity;

@ExtendWith(MockitoExtension.class)
public class FoodServiceTest {
	
	@Mock
	private FoodRepository repository;
	
	@InjectMocks
	private FoodService service;
	
	@Test
	public void testGetAllFoods() {
		ArrayList<FoodEntity> foods = new ArrayList<FoodEntity>();
		FoodEntity food1 = new FoodEntity(Long.valueOf(1), "cacao", (float) 20.00, (float) 12.45, (float) 9.30);
		FoodEntity food2 = new FoodEntity(Long.valueOf(2), "rice", (float) 15.00, (float) 7.45, (float) 8.30);
		foods.addAll(List.of(food1, food2));
		when(this.repository.findAll()).thenReturn(foods);
		assertThat(this.service.getAllFoods()).isEqualTo(foods);
	}
}
