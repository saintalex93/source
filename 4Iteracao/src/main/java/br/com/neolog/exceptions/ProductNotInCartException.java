package br.com.neolog.exceptions;

/**
 * Fazer
 */

public class ProductNotInCartException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param message
	 *            para informar que o objeto passado como argumento
	 */
	public ProductNotInCartException(String message) {
		super(message);
	}

}
