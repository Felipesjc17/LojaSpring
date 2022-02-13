package com.felipesjc.loja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipesjc.loja.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
