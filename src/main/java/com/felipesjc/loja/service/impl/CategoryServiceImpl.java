package com.felipesjc.loja.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.felipesjc.loja.model.Category;
import com.felipesjc.loja.repositories.CategoryRepository;
import com.felipesjc.loja.service.CategoryService;
import com.felipesjc.loja.service.impl.exceptions.DatabaseException;
import com.felipesjc.loja.service.impl.exceptions.ResourceNotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Override
	public List<Category> findAll() {
		return repository.findAll();
	}

	@Override
	public Category findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		return obj.get();
	}

	@Override
	public Category insert(Category obj) {
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
	public Category update(Long id, Category obj) {
		try {
			Category entity = repository.getById(id);
			updateData(entity, obj);
			return repository.save(entity);
			}
			catch(EntityNotFoundException e) {
				throw new ResourceNotFoundException(id);
			}
		}


	private void updateData(Category entity, Category obj) {
	entity.setName(obj.getName());
	}

}
