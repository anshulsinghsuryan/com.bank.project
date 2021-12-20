package com.bank.application.dto;

import javax.validation.constraints.Pattern;

public class ResetPasswordDto2 {

	@Pattern(regexp="^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$",message="Password should be within 8 to 20 characters containing alpha numeric uppercase lowercase and atleast one symbol !!")
	private String password;
	
	@Pattern(regexp="^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$",message="Password should be within 8 to 20 characters containing alpha numeric uppercase lowercase and atleast one symbol !!")
	private String confirmPassword;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
}
