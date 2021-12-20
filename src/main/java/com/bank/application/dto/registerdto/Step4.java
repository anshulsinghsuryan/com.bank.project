package com.bank.application.dto.registerdto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Step4 {

	@NotNull(message ="Date of Birth can not be empty !!")
	@NotBlank(message="Date of Birth can not be blank !!")
	private String dateOfBirth;
	
	@NotNull(message ="OTP can not be empty !!")
	@NotBlank(message="OTP can not be blank !!")
	@Pattern(regexp="^\\d{6}$",message="OTP is invalid !!")
	private String otp;
	
	@Pattern(regexp="^\\d{12}$",message="Adhar number is invalid !!")
	private String adhar;
	
	@Size(min=10,max=10,message="PAN should be 10 characters !!")
	@NotNull(message ="PAN can not be empty !!")
	@NotBlank(message="PAN can not be blank !!")
	private String pan;
	
	@Size(min=10,max=10,message="Voter ID Card should be 10 characters !!")
	@NotNull(message ="Voter ID Card can not be empty !!")
	@NotBlank(message="Voter ID Card can not be blank !!")
	private String voterIdCard;
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAdhar() {
		return adhar;
	}
	public void setAdhar(String adhar) {
		this.adhar = adhar;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getVoterIdCard() {
		return voterIdCard;
	}
	public void setVoterIdCard(String voterIdCard) {
		this.voterIdCard = voterIdCard;
	}	
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
}
