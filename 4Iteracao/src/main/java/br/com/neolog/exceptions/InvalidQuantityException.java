package br.com.neolog.exceptions;

/**
 * Lançada para informar que a quantidade informada como argumento do método não
 * é valida.
 */
public class InvalidQuantityException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param message
	 *            A mensagem para informar o cliente que a quantidade é
	 *            inválida.
	 */

	public InvalidQuantityException(String message) {
		super(message);
	}

}
