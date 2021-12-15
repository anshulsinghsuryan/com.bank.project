package com.bank.application.dto.registerdto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Step1 {

	@Size(min=3,max=20,message="First Name should be between 3 to 20 characters !!")
	@NotNull(message ="First Name can not be empty !!")
	@NotBlank(message="First Name can not be blank !!")
	private String firstName;
	
	@Size(min=3,max=20,message="Last Name should be between 3 to 20 characters !!")
	@NotNull(message ="Last Name can not be empty !!")
	@NotBlank(message="Last Name can not be blank !!")
	private String lastName;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
