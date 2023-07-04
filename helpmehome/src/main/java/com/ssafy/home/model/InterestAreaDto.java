package com.ssafy.home.model;

public class InterestAreaDto {

	private int idx;
	private String userId;
	private String dongCode;
	private String area;
	
	public InterestAreaDto() {}

	public InterestAreaDto(int idx, String userId, String dongCode, String area) {
		super();
		this.idx = idx;
		this.userId = userId;
		this.dongCode = dongCode;
		this.area = area;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "InterestAreaDto [idx=" + idx + ", userId=" + userId + ", dongCode=" + dongCode + ", area=" + area + "]";
	}
	
}
