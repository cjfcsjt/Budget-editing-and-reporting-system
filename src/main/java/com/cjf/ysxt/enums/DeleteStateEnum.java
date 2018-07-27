package com.cjf.ysxt.enums;

public enum DeleteStateEnum {

	SUCCESS(1, "删除成功"), NO_NUMBER(-1, "不存在"), INNER_ERROR(-2, "系统异常");

	private int state;

	private String stateInfo;

	private DeleteStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static DeleteStateEnum stateOf(int index) {
		for (DeleteStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
