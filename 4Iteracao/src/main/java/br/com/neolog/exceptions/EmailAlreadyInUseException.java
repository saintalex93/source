package br.com.neolog.exceptions;

/**
 * Lançada quando o email informado já está em uso.
 * 
 * @author igor.kurman
 * 
 */
public class EmailAlreadyInUseException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param message
	 */
	public EmailAlreadyInUseException(String message) {
		super(message);
	}

}
