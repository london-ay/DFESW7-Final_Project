package com.qa.userapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FoodEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private float carbs;
	
	@Column
	private float fat;
	
	@Column
	private float protein;

	public FoodEntity(Long id, String name, float carbs, float fat, float protein) {
		super();
		this.id = id;
		this.name = name;
		this.carbs = carbs;
		this.fat = fat;
		this.protein = protein;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getCarbs() {
		return carbs;
	}

	public void setCarbs(float carbs) {
		this.carbs = carbs;
	}

	public float getFat() {
		return fat;
	}

	public void setFat(float fat) {
		this.fat = fat;
	}

	public float getProtein() {
		return protein;
	}

	public void setProtein(float protein) {
		this.protein = protein;
	}

	@Override
	public String toString() {
		return "FoodEntity [id=" + id + ", name=" + name + ", carbs=" + carbs + ", fat=" + fat + ", protein=" + protein
				+ "]";
	}
}
