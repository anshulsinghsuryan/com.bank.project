package com.bank.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.application.entity.User;
import com.bank.application.enums.Constants;
import com.bank.application.repository.UserRepository;

@Service
public class ManagerService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> getUsers(){
		return userRepository.findUserByRole(Constants.USER.toString());
	}
	
	public void approve(Long id) {
		userRepository.updateStatus(Constants.APPROVED.toString(), id);
	}
	
	public void reject(Long id) {
		userRepository.updateStatus(Constants.REJECTED.toString(), id);
	}
	
	public void inreview(Long id) {
		userRepository.updateStatus(Constants.INREVIEW.toString(), id);
	}
	
	public void pending(Long id) {
		userRepository.updateStatus(Constants.PENDING.toString(), id);
	}
}
