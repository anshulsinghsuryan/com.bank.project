package com.bank.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bank.application.dto.ResetPasswordDto2;
import com.bank.application.repository.UserRepository;

@Service
public class PasswordResetService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public void resetPassword(ResetPasswordDto2 resetDto,String email) {
		userRepository.resetPassword(encoder.encode(resetDto.getPassword()),email);
	}
}
