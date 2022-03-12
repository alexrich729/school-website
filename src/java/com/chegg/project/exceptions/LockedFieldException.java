package com.chegg.project.exceptions;

/**
 * Represents scenario when completing the operation would require updating fields that have been
 *   marked as "locked" (should not be updated.)
 * @author alexrich729
 *
 */

public class LockedFieldException extends Exception {
	
	/**
	 * For serializing - not likely used.
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String DEFAULT_MSG = "Locked field would be updated.";

	public LockedFieldException() {
		super(DEFAULT_MSG);
	}

	public LockedFieldException(String message) {
		super(message);
	}

	public LockedFieldException(Throwable cause) {
		super(DEFAULT_MSG, cause);
	}

	public LockedFieldException(String message, Throwable cause) {
		super(message, cause);
	}

	public LockedFieldException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
