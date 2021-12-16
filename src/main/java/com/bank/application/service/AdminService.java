package com.bank.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.application.entity.User;
import com.bank.application.enums.Constants;
import com.bank.application.repository.UserRepository;

@Service
public class AdminService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUser(){
		return userRepository.findAll();
	}
	
	public void apointManager(Long id) {
		userRepository.updateRole(Constants.MANAGER.toString(), id);
	}
	public void apointUser(Long id) {
		userRepository.updateRole(Constants.USER.toString(), id);
	}
	public void apointAdmin(Long id) {
		userRepository.updateRole(Constants.ADMIN.toString(), id);
	}
}
