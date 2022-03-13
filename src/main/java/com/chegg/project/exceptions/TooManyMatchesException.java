package com.chegg.project.exceptions;

/**
 * Represents scenario when more matches are found than the caller expects, e.g. when going to update single
 *   record, but multiple records match the selector.
 * @author alexrich729
 *
 */

public class TooManyMatchesException extends Exception {
	
	/**
	 * For serializing - not likely used.
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String DEFAULT_MSG = "Found more matches than expected.";

	public TooManyMatchesException() {
		super(DEFAULT_MSG);
	}

	public TooManyMatchesException(String message) {
		super(message);
	}

	public TooManyMatchesException(Throwable cause) {
		super(DEFAULT_MSG, cause);
	}

	public TooManyMatchesException(String message, Throwable cause) {
		super(message, cause);
	}

	public TooManyMatchesException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
