package com.felipesjc.loja.service.impl.exceptions;

/**
 * Classe para tratar excessões das classes de services
 *
 * 
 * 
 * @author Felipesjc17
 */


public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(Object id) {
		super("Resource not found. id " + id);
	}

}
