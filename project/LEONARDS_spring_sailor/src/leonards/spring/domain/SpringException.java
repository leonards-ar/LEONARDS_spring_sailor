/*
 * Project: Spring Sailor
 * This class is member of leonards.spring.domain
 * File: SpringException.java
 *
 * Property of Leonards / Mindpool
 * Created on 20/06/2004
 */
package leonards.spring.domain;

import leonards.common.base.NestedException;

/**
 * @author Mariano
 *
 * This class is the abstraction of
 */
public class SpringException extends NestedException {

	/**
	 * 
	 */
	public SpringException() {
		super();
	}

	/**
	 * @param message
	 */
	public SpringException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SpringException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param code
	 * @param message
	 * @param cause
	 */
	public SpringException(int code, String message, Throwable cause) {
		super(code, message, cause);
	}

	/**
	 * @param code
	 * @param cause
	 */
	public SpringException(int code, Throwable cause) {
		super(code, cause);
	}

	/**
	 * @param code
	 * @param message
	 */
	public SpringException(int code, String message) {
		super(code, message);
	}

	/**
	 * @param cause
	 */
	public SpringException(Throwable cause) {
		super(cause);
	}

}
