package br.com.neolog.ecommerce.exceptions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ErrorDetails {
	private final String date;
	private final String message;
	private final String details;
	private final String exception;
	private final String error;
	private final int httpCode;

	public ErrorDetails(final String message, final String exception, final String details, final String error,
			final int httpCode) {
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		this.date = simpleDateFormat.format(new Date());

		this.message = message;
		this.exception = exception;
		this.details = details;
		this.error = error;
		this.httpCode = httpCode;
	}

	public String getDate() {
		return date;
	}

	public String getException() {
		return exception;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	public String getError() {
		return error;
	}

	public int getHttpCode() {
		return httpCode;
	}
}
