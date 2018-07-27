package com.cjf.ysxt.enums;

public enum InsertStateEnum {

	SUCCESS(1, "插入成功"), REPEAT_INSERT(-1, "重复插入"), INNER_ERROR(-2, "系统异常");

	private int state;

	private String stateInfo;

	private InsertStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static InsertStateEnum stateOf(int index) {
		for (InsertStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
