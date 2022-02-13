package com.felipesjc.loja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipesjc.loja.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
