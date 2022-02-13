package com.felipesjc.loja.service.impl.exceptions;

/**
 * Classe para tratar excessões das classes de services
 *
 * 
 * 
 * @author Felipesjc17
 */

public class DatabaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DatabaseException(String msg) {
		super(msg);
	}

}
