package com.cjf.ysxt.exception;

/**
 * 重复预约异常
 */
public class RepeatException extends RuntimeException {

	public RepeatException(String message) {
		super(message);
	}

	public RepeatException(String message, Throwable cause) {
		super(message, cause);
	}

}
