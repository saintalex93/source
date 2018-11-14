package br.com.neolog.exceptions;

/**
 * Lançada para informar que a {@link Category} não foi encontrada.
 */

public class CategoryNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param message
	 *            A mensagem detalhada, usada para informar que a
	 *            {@link Category} não foi encontrada.
	 */
	public CategoryNotFoundException(String message) {
		super(message);
	}
}
