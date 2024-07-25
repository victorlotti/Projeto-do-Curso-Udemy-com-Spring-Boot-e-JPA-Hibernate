package com.spring.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.course.entities.OrderItem;
import com.spring.course.entities.pk.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK>{

}
