package com.cjf.ysxt.exception;

/**
 * 账号密码错误异常
 */
public class NoNumberException extends RuntimeException {

	public NoNumberException(String message) {
		super(message);
	}

	public NoNumberException(String message, Throwable cause) {
		super(message, cause);
	}

}
