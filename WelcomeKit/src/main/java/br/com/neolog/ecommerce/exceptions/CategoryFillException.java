package br.com.neolog.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CategoryFillException extends RuntimeException {

	private static final long serialVersionUID = -4510225735739862876L;

	public CategoryFillException(final String exception) {
		super(exception);
	}

}
