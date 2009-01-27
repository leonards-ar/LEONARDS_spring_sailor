/*
 * Created on Jul 25, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package leonards.spring.domain;

/**
 * @author Mariano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UsernameAlreadyExistsException extends SpringException {

	/**
	 * 
	 */
	public UsernameAlreadyExistsException() {
		super();
	}

	/**
	 * @param message
	 */
	public UsernameAlreadyExistsException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UsernameAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param code
	 * @param message
	 * @param cause
	 */
	public UsernameAlreadyExistsException(
		int code,
		String message,
		Throwable cause) {
		super(code, message, cause);
	}

	/**
	 * @param code
	 * @param cause
	 */
	public UsernameAlreadyExistsException(int code, Throwable cause) {
		super(code, cause);
	}

	/**
	 * @param code
	 * @param message
	 */
	public UsernameAlreadyExistsException(int code, String message) {
		super(code, message);
	}

	/**
	 * @param cause
	 */
	public UsernameAlreadyExistsException(Throwable cause) {
		super(cause);
	}

}
