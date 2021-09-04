package com.foodplaza.Repositories;

import com.foodplaza.Entities.Dishes;
import com.foodplaza.Entities.Orders;
import com.foodplaza.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    @Query(value = "SELECT * FROM foodplaza.orders where user_id = ?1", nativeQuery = true)
    List<Orders> findAllByUser(Long id);
}