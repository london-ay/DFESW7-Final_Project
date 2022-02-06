package com.qa.nutritionapp.data;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<FoodEntity, Long> {
	List<FoodEntity> findFoodEntityByName(String name);
}
