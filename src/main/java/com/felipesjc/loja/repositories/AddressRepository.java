package com.felipesjc.loja.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.felipesjc.loja.model.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, String>{

}
