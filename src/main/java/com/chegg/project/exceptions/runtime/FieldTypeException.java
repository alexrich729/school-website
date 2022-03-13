package com.chegg.project.exceptions.runtime;

/**
 * Represents scenario when attempting to use a field with type that does not match what is configured for the entity.
 * @author alexrich729
 *
 */

public class FieldTypeException extends RuntimeException {
	
	/**
	 * For serializing - not likely used.
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String DEFAULT_MSG = "Attempt to use a field not supported by the entity.";


	public FieldTypeException() {
		super(DEFAULT_MSG);
	}

	public FieldTypeException(String message) {
		super(message);
	}

	public FieldTypeException(Throwable cause) {
		super(cause);
	}

	public FieldTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public FieldTypeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
