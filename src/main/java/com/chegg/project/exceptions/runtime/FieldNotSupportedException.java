package com.chegg.project.exceptions.runtime;

/**
 * Represents scenario when attempting to use a field that is not supported by a given entity type.
 * @author alexrich729
 *
 */

public class FieldNotSupportedException extends RuntimeException {
	
	/**
	 * For serializing - not likely used.
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String DEFAULT_MSG = "Attempt to use a field not supported by the entity.";


	public FieldNotSupportedException() {
		super(DEFAULT_MSG);
	}

	public FieldNotSupportedException(String message) {
		super(message);
	}

	public FieldNotSupportedException(Throwable cause) {
		super(cause);
	}

	public FieldNotSupportedException(String message, Throwable cause) {
		super(message, cause);
	}

	public FieldNotSupportedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
