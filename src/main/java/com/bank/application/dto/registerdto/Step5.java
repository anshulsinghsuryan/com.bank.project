package com.bank.application.dto.registerdto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Step5 {

	@NotNull(message ="Address Line 1 can not be empty !!")
	@NotBlank(message="Address Line 1 can not be blank !!")
	private String addressLine1;
	
	@NotNull(message ="Address Line 2 can not be empty !!")
	@NotBlank(message="Address Line 2 can not be blank !!")
	private String addressLine2;
	
	@NotNull(message ="Town can not be empty !!")
	@NotBlank(message="Town of Birth can not be blank !!")
	private String town;
	
	@NotNull(message ="Area Code can not be empty !!")
	@NotBlank(message="Area Code can not be blank !!")
	@Pattern(regexp="^\\d{6}$",message="Area Code is invalid !!")
	private String areaCode;
	
	private String district;
	private String state;
	private String country;
	
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addresLine1) {
		this.addressLine1 = addresLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
