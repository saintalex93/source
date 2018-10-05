package br.com.neolog.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CategoryRemoveWithProductException extends RuntimeException {

	private static final long serialVersionUID = 7243396709225776929L;

	public CategoryRemoveWithProductException() {
		super("Não é possivel remover uma categoria com produtos atrelados a ela.");
	}

}
