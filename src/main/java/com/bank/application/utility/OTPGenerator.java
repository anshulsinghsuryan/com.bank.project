package com.bank.application.utility;

import java.util.Random;

public class OTPGenerator {

	private static String otp;
	
	public static String generateOTP() {
		StringBuffer string = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < 50; i++) {
			string.append(random.nextInt(10));
		}
		otp = string.toString().substring(24, 30);
		return otp;
	}
}
