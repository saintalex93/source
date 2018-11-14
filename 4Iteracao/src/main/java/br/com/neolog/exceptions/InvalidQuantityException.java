package br.com.neolog.exceptions;

/**
 * Lan�ada para informar que a quantidade informada como argumento do m�todo n�o
 * � valida.
 */
public class InvalidQuantityException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param message
	 *            A mensagem para informar o cliente que a quantidade �
	 *            inv�lida.
	 */

	public InvalidQuantityException(String message) {
		super(message);
	}

}
