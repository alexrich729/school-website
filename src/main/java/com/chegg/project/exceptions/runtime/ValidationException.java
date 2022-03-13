package com.chegg.project.exceptions.runtime;

/**
 * Represents scenario when attempting to create an object but fields are incorrect.
 * @author alexrich729
 *
 */
public class ValidationException extends RuntimeException {

	/**
	 * For serializing - not likely used.
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String DEFAULT_MSG = "Attempt to create an object using incorrect fields.";


	public ValidationException() {
		super(DEFAULT_MSG);
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(Throwable cause) {
		super(DEFAULT_MSG, cause);
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
