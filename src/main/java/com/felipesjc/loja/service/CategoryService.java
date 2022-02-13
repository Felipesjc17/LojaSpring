package com.felipesjc.loja.service;

import java.util.List;

import com.felipesjc.loja.model.Category;

public interface CategoryService {
	
	public List<Category> findAll();
	
	public Category findById(Long id);
	
	public Category insert(Category obj);
	
	public void delete(Long id);
	
	public Category update(Long id, Category obj);

}
