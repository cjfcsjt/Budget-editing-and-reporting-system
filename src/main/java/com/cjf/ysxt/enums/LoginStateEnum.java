package com.cjf.ysxt.enums;

public enum LoginStateEnum {

	SUCCESS(1, "登录成功"), NO_NUMBER(0, "账号密码错误"), REPEAT_LOGIN(-1, "重复登录"), INNER_ERROR(-2, "系统异常");

	private int state;

	private String stateInfo;

	private LoginStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static LoginStateEnum stateOf(int index) {
		for (LoginStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
