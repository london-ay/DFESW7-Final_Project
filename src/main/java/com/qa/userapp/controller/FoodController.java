package com.qa.userapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.userapp.data.FoodEntity;
import com.qa.userapp.service.FoodService;

@RestController
@RequestMapping(path = "/food")
public class FoodController {
	
	private FoodService service;
	
	@Autowired
	public FoodController(FoodService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<FoodEntity>> getFoods() {
		ResponseEntity<List<FoodEntity>> foods = ResponseEntity.ok(service.getAllFoods());
		return foods;
	}
	
	@GetMapping(path = "/id/{id}")
	public ResponseEntity<FoodEntity> getFoodByID(@PathVariable("id") Long id) {
		FoodEntity food = service.getFoodByID(id);
		ResponseEntity<FoodEntity> response = ResponseEntity.ok(food);
		return response;
	}
	
	@GetMapping(path = "/name/{name}")
	public ResponseEntity<List<FoodEntity>> getFoodByName(@PathVariable("name") String name) {
		List<FoodEntity> food = service.getFoodByName(name);
		ResponseEntity<List<FoodEntity>> response = ResponseEntity.ok(food);
		return response;
	}
	
	@PostMapping(path = "/create")
	public ResponseEntity<FoodEntity> createFood(@RequestBody FoodEntity food) {
		FoodEntity savedFood = this.service.createFood(food);
		ResponseEntity<FoodEntity> response = ResponseEntity.ok(savedFood);
		return response;
	}
}
