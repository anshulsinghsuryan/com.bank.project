package com.bank.application.dto.registerdto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class Step3 {

	@Email(regexp="^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",message="Email is Invalid")
	private String email;
	
	@Pattern(regexp="^\\d{10}$",message="Mobile number is invalid !!")
	private String mobile;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
