package com.felipesjc.loja.service;

import java.util.List;

import com.felipesjc.loja.model.Product;

public interface ProductService {

	public List<Product> findAll();
	
	public Product findById(Long id);
	
	public Product insert(Product obj);
	
	public void delete(Long id);
	
	public Product update(Long id, Product obj);
}
