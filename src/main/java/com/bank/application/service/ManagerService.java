package com.bank.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.application.dto.UserDto;
import com.bank.application.entity.User;
import com.bank.application.enums.Constants;
import com.bank.application.repository.UserRepository;

@Service
public class ManagerService {

	@Autowired
	private UserRepository userRepository;
	
	public List<UserDto> getUsers(){
		List<UserDto> userList = new ArrayList<UserDto>();
		
		userRepository.findUserByRole(Constants.USER.toString()).forEach(item -> {
			UserDto userDto = convertEntity(item);
			userList.add(userDto);
		});
		
		return userList;
	}
	
	private UserDto convertEntity(User user) {
		UserDto userDto = new UserDto();
		userDto.setAddressLine1(user.getAddressLine1());
		userDto.setAddressLine2(user.getAddressLine2());
		userDto.setAdhar(user.getAdhar());
		userDto.setAreaCode(user.getAreaCode());
		userDto.setCountry(user.getCountry());
		userDto.setDateOfBirth(user.getDateOfBirth());
		userDto.setDistrict(user.getDistrict());
		userDto.setEmail(user.getEmail());
		userDto.setFather(user.getFather());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setMobile(user.getMobile());
		userDto.setMother(user.getMother());
		userDto.setPan(user.getPan());
		userDto.setState(user.getState());
		userDto.setStatus(user.getStatus());
		userDto.setTown(user.getTown());
		userDto.setVoterIdCard(user.getVoterIdCard());
		userDto.setProfilePicture(user.getProfilePicture());
		userDto.setId(user.getId());
		return userDto;
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
	
	public List<User> getInreviewUser(){
		return userRepository.findUserByStatus(Constants.INREVIEW.toString());
	}
	
	public List<User> getRejectedUser(){
		return userRepository.findUserByStatus(Constants.REJECTED.toString());
	}
	
	public List<User> getPendingUser(){
		return userRepository.findUserByStatus(Constants.PENDING.toString());
	}
	
	public List<User> getApprovedUser(){
		return userRepository.findUserByStatus(Constants.APPROVED.toString());
	}
}
