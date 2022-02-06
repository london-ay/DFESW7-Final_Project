package com.qa.nutritionapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.nutritionapp.data.FoodEntity;
import com.qa.nutritionapp.data.FoodRepository;

@Service
public class FoodService {
	private FoodRepository repository;
	
	@Autowired // dependency injection
	public FoodService(FoodRepository repository) {
		this.repository = repository;
	}
	
	public List<FoodEntity> getAllFoods() {
		return this.repository.findAll();
	}
	
	public FoodEntity getFoodByID(Long ID) {
		return this.repository.findById(ID).orElseThrow();
	}
	
	public List<FoodEntity> getFoodByName(String name) {
		return this.repository.findFoodEntityByName(name);
	}
	
	public FoodEntity createFood(FoodEntity food) {
		FoodEntity savedFood = this.repository.save(food);
		return savedFood;
	}
	
	public FoodEntity updateFood(FoodEntity updatedFood, Long ID) {
		FoodEntity food = this.repository.findById(ID).orElseThrow();
		food.setName(updatedFood.getName());
		food.setCarbs(updatedFood.getCarbs());
		food.setFat(updatedFood.getFat());
		food.setProtein(updatedFood.getProtein());
		this.repository.save(food);
		return food;
	}
	
	public void deleteFoodByID(Long ID) {
		this.repository.deleteById(ID);
	}
}
