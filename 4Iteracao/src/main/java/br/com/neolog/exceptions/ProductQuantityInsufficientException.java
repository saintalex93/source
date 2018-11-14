package br.com.neolog.exceptions;

/**
 * Lançada quando uma quantidade passada como argumento é insuficiente.
 * 
 * @author igor.kurman
 * 
 */

public class ProductQuantityInsufficientException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param message
	 *            É a mensagem usada para informar o cliente que a quantidade
	 *            passada como argumento é insuficiente.
	 */
	public ProductQuantityInsufficientException(String message) {
		super(message);
	}

}
