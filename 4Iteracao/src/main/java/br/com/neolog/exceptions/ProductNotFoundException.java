package br.com.neolog.exceptions;

/**
 * Lançada para indicar que o objeto informado como argumento não foi
 * encontrado.
 */

public class ProductNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param message
	 *            A mensagem detalhada, usada para informar o cliente que o
	 *            produto nao se encontra na base de sados.
	 */
	public ProductNotFoundException(String message) {
		super(message);
	}

}
