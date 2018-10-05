package br.com.neolog.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class StockNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2186990687516619812L;

	public StockNotFoundException() {
		super("Estoque não encontrado");
	}

}
