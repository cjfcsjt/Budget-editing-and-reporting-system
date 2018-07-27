package com.cjf.ysxt.exception;

/**
 * 重复预约异常
 */
public class RepeatLoginException extends RuntimeException {

	public RepeatLoginException(String message) {
		super(message);
	}

	public RepeatLoginException(String message, Throwable cause) {
		super(message, cause);
	}

}
