package com.felipesjc.loja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipesjc.loja.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
