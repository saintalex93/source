package br.com.neolog.exceptions;

/**
 * Lan�ada quando uma quantidade passada como argumento � insuficiente.
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
	 *            � a mensagem usada para informar o cliente que a quantidade
	 *            passada como argumento � insuficiente.
	 */
	public ProductQuantityInsufficientException(String message) {
		super(message);
	}

}
