package com.ssafy.home.model;

public class DealInfoDto {

	private int dealYear;
	private int maxAmount;
	private int minAmount;
	public DealInfoDto(int dealYear, int maxAmount, int minAmount) {
		super();
		this.dealYear = dealYear;
		this.maxAmount = maxAmount;
		this.minAmount = minAmount;
	}
	public int getDealYear() {
		return dealYear;
	}
	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}
	public int getMaxAmount() {
		return maxAmount;
	}
	public void setMaxAmount(int maxAmount) {
		this.maxAmount = maxAmount;
	}
	public int getMinAmount() {
		return minAmount;
	}
	public void setMinAmount(int minAmount) {
		this.minAmount = minAmount;
	}
	@Override
	public String toString() {
		return "DealInfoDto [dealYear=" + dealYear + ", maxAmount=" + maxAmount + ", minAmount=" + minAmount + "]";
	}
	
	
}
