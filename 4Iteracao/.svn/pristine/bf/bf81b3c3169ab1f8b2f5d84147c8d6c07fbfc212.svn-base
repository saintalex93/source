package br.com.neolog.exceptions;

import br.com.neolog.models.Product;
import br.com.neolog.models.Stock;

/**
 * Lançada para informar que o {@link Product} existe em estoque (
 * {@link Stock}) e não pode ser excluído.
 */

public class ProductInStockException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param message
	 *            A mensagem detalhada, usada para informar o cliente que o
	 *            produto nao se encontra na base de sados.
	 */
	public ProductInStockException(String message) {
		super(message);
	}

}
