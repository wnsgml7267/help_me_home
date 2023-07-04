package com.ssafy.home.model;

public class HomeResultDto {
	private String aptCode;
	private String buildYear;
	private String apartmentName;
	private String floor;
	private String area;
	private String dongCode;
	private String dongName;
	private String dealAmount;
	private String lng;
	private String lat;
	
	public HomeResultDto() {}

	public HomeResultDto(String aptCode, String buildYear, String apartmentName, String floor, String area,
			String dongCode, String dongName, String dealAmount, String lng, String lat) {
		super();
		this.aptCode = aptCode;
		this.buildYear = buildYear;
		this.apartmentName = apartmentName;
		this.floor = floor;
		this.area = area;
		this.dongCode = dongCode;
		this.dongName = dongName;
		this.dealAmount = dealAmount;
		this.lng = lng;
		this.lat = lat;
	}

	public String getAptCode() {
		return aptCode;
	}

	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}

	public String getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}

	public String getApartmentName() {
		return apartmentName;
	}

	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	public String getDongName() {
		return dongName;
	}

	public void setDongName(String dongName) {
		this.dongName = dongName;
	}

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	@Override
	public String toString() {
		return "HomeResultDto [aptCode=" + aptCode + ", buildYear=" + buildYear + ", apartmentName=" + apartmentName
				+ ", floor=" + floor + ", area=" + area + ", dongCode=" + dongCode + ", dongName=" + dongName
				+ ", dealAmount=" + dealAmount + ", lng=" + lng + ", lat=" + lat + "]";
	}

	

	
}
