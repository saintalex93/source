package br.com.neolog.exceptions;

/**
 * 
 * 
 * @author igor.kurman
 * 
 */
public class EmptyCartException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param message
	 */
	public EmptyCartException(String message) {
		super(message);
	}

}
