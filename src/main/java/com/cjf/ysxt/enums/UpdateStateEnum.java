package com.cjf.ysxt.enums;

public enum UpdateStateEnum {

	SUCCESS(1, "更新成功"), NO_NUMBER(-1, "不存在该条目"),INNER_ERROR(-2, "系统异常");

	private int state;

	private String stateInfo;

	private UpdateStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static UpdateStateEnum stateOf(int index) {
		for (UpdateStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
