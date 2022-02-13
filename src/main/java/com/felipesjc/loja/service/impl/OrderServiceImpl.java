package com.felipesjc.loja.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.felipesjc.loja.model.Order;
import com.felipesjc.loja.repositories.OrderRepository;
import com.felipesjc.loja.service.OrderService;
import com.felipesjc.loja.service.impl.exceptions.DatabaseException;
import com.felipesjc.loja.service.impl.exceptions.ResourceNotFoundException;


@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository repository;
	
	@Override
	public List<Order> findAll(){
		return repository.findAll();
	}
	
	@Override
	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.get();
	}

	@Override
	public Order insert(Order obj) {		
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
	public Order update(Long id, Order obj) {
		try {
			Order entity = repository.getById(id);
			updateData(entity, obj);
			return repository.save(entity);
			}
			catch(EntityNotFoundException e) {
				throw new ResourceNotFoundException(id);
			}
		}


	private void updateData(Order entity, Order obj) {
		entity.setClient(obj.getClient());
		entity.setMoment(obj.getMoment());
		entity.setOrderStatus(obj.getOrderStatus());
		entity.setPayment(obj.getPayment());
		}
}
