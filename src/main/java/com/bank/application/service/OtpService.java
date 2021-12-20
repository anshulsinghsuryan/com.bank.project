package com.bank.application.service;

import org.springframework.stereotype.Service;

import com.bank.application.utility.OTPGenerator;

@Service
public class OtpService {

	private String otp;
	
	public String getOTP() {
		this.otp = OTPGenerator.generateOTP();
		return otp;
	}
	
	public boolean verifyOTP(String otp) {
		return this.otp.equals(otp);
	}
}
