package br.com.neolog.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CategoryDuplicatedNameException extends RuntimeException {

	private static final long serialVersionUID = 5694125028754197302L;

	public CategoryDuplicatedNameException() {
		super("O nome da categoria já existe");

	}

}
