package com.cjf.ysxt.exception;

public class deleteException extends RuntimeException {

	public deleteException(String message) {
		super(message);
	}

	public deleteException(String message, Throwable cause) {
		super(message, cause);
	}
}
