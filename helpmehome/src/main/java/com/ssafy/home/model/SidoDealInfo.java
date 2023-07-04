package com.ssafy.home.model;

public class SidoDealInfo {

	private int dealYear;
	private int cnt;
	public SidoDealInfo(int dealYear, int cnt) {
		super();
		this.dealYear = dealYear;
		this.cnt = cnt;
	}
	public int getDealYear() {
		return dealYear;
	}
	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	
	
}
