package com.felipesjc.loja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipesjc.loja.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
