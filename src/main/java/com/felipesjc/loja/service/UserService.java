package com.felipesjc.loja.service;

import java.util.List;

import com.felipesjc.loja.model.User;

public interface UserService {
	
	public List<User> findAll();
	
	public User findById(Long id);
	
	public User insert(User obj);
	
	public void delete(Long id);
	
	public User update(Long id, User obj);

}
