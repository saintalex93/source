package br.com.neolog.ecommerce.exceptions;

import java.util.Date;

public class ErrorDetails {

	private final Date timestamp;
	private final String message;
	private final String details;

	public ErrorDetails(final Date timestamp, final String message, final String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

}
