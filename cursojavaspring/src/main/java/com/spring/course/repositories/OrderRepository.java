package com.spring.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.course.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
