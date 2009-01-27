/*
 * Created on Aug 1, 2004
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
public class ReservationAlreadyExistsException extends SpringException {

	/**
	 * 
	 */
	public ReservationAlreadyExistsException() {
		super();
	}

	/**
	 * @param message
	 */
	public ReservationAlreadyExistsException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ReservationAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param code
	 * @param message
	 * @param cause
	 */
	public ReservationAlreadyExistsException(
		int code,
		String message,
		Throwable cause) {
		super(code, message, cause);
	}

	/**
	 * @param code
	 * @param cause
	 */
	public ReservationAlreadyExistsException(int code, Throwable cause) {
		super(code, cause);
	}

	/**
	 * @param code
	 * @param message
	 */
	public ReservationAlreadyExistsException(int code, String message) {
		super(code, message);
	}

	/**
	 * @param cause
	 */
	public ReservationAlreadyExistsException(Throwable cause) {
		super(cause);
	}

}
