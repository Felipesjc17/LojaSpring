package com.felipesjc.loja.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.felipesjc.loja.model.Product;
import com.felipesjc.loja.repositories.ProductRepository;
import com.felipesjc.loja.service.ProductService;
import com.felipesjc.loja.service.impl.exceptions.DatabaseException;
import com.felipesjc.loja.service.impl.exceptions.ResourceNotFoundException;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository repository;
	
	@Override
	public List<Product> findAll(){
		return repository.findAll();
	}
	
	@Override
	public Product findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.get();
	}

	@Override
	public Product insert(Product obj) {
		return repository.save(obj);
	}

	@Override
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
			
	}

	@Override
	public Product update(Long id, Product obj) {
		try {
			Product entity = repository.getById(id);
			updateData(entity, obj);
			return repository.save(entity);
			}
			catch(EntityNotFoundException e) {
				throw new ResourceNotFoundException(id);
			}
		}
	private void updateData(Product entity, Product obj) {
		entity.setName(obj.getName());
		entity.setPrice(obj.getPrice());
		entity.setDescription(obj.getDescription());
		entity.setImgUrl(obj.getImgUrl());
		}

}
