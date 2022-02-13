package com.felipesjc.loja.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.felipesjc.loja.model.Address;
import com.felipesjc.loja.model.User;
import com.felipesjc.loja.repositories.AddressRepository;
import com.felipesjc.loja.repositories.UserRepository;
import com.felipesjc.loja.service.UserService;
import com.felipesjc.loja.service.ViaCepService;
import com.felipesjc.loja.service.impl.exceptions.DatabaseException;
import com.felipesjc.loja.service.impl.exceptions.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private ViaCepService viaCepService;
	
	@Override
	public List<User> findAll(){
		return repository.findAll();
	}
	
	@Override
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	@Override
	public User insert(User user) {
		return saveUser(user);
	}
	
	@Override
	public void delete(Long id) {
		try {
		repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	@Override
	public User update(Long id, User obj) {
		try {
		User user = repository.getById(id);
		updateData(user, obj);
		return repository.save(user);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	
	private void updateData(User user, User obj) {
		String cep = obj.getAddress().getCep();
		Address address = checkAddress(cep, obj);
		user.setAddress(address);
		user.setName(obj.getName());
		user.setEmail(obj.getEmail());
		user.setPhone(obj.getPhone());
	}
	
	private User saveUser(User user) {
		String cep = user.getAddress().getCep();
		Address address = checkAddress(cep, user);		
		user.setAddress(address);
		
		return repository.save(user);
	}

	private Address checkAddress(String cep, User user) {
		Address address = addressRepository.findById(cep).orElseGet(()-> {
			Address newAddress = viaCepService.consultCep(cep);
			newAddress.setNumber(user.getAddress().getNumber());
			addressRepository.save(newAddress);
			return newAddress;
		});
		return address;
	}
	
}
