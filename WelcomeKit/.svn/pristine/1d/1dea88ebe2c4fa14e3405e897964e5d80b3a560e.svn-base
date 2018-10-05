package br.com.neolog.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CategoryDuplicatedCodeException extends RuntimeException {

	private static final long serialVersionUID = -8759339050090093523L;

	public CategoryDuplicatedCodeException() {
		super("Código da categoria duplicado");
	}

}
