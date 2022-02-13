package com.felipesjc.loja.service;

import java.util.List;

import com.felipesjc.loja.model.Order;

public interface OrderService {

	public List<Order> findAll();
	
	public Order findById(Long id);
	
	public Order insert(Order obj);
	
	public void delete(Long id);
	
	public Order update(Long id, Order obj);
	
	
}
