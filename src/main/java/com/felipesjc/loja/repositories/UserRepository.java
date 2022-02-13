package com.felipesjc.loja.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.felipesjc.loja.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
