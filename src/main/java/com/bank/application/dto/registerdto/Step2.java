package com.bank.application.dto.registerdto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Step2 {

	@Size(min=3,max=20,message="Father Name should be between 3 to 20 characters !!")
	@NotNull(message ="Father Name can not be empty !!")
	@NotBlank(message="Father Name can not be blank !!")
	private String fatherName;
	
	@Size(min=3,max=20,message="Mother Name should be between 3 to 20 characters !!")
	@NotNull(message ="Mother Name can not be empty !!")
	@NotBlank(message="Mother Name can not be blank !!")
	private String motherName;
	
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	
	
}
