package com.foodplaza.Repositories;

import com.foodplaza.Entities.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishesRepository extends JpaRepository<Dishes, Long> {
}