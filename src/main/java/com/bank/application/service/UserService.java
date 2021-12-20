package com.bank.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.application.dto.UserDto;
import com.bank.application.entity.User;
import com.bank.application.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	private UserDto userDto = new UserDto();
	
	public UserDto getUserStatus(String email) {
		User user = userRepository.findUserByEmail(email);
		convertEntity(user);
		return this.userDto;
	}
	
	public UserDto getUserStatus(Long id) {
		User user = userRepository.findById(id).get();
		convertEntity(user);
		return this.userDto;
	}
	
	private void convertEntity(User user) {
		this.userDto.setAddressLine1(user.getAddressLine1());
		this.userDto.setAddressLine2(user.getAddressLine2());
		this.userDto.setAdhar(user.getAdhar());
		this.userDto.setAreaCode(user.getAreaCode());
		this.userDto.setCountry(user.getCountry());
		this.userDto.setDateOfBirth(user.getDateOfBirth());
		this.userDto.setDistrict(user.getDistrict());
		this.userDto.setEmail(user.getEmail());
		this.userDto.setFather(user.getFather());
		this.userDto.setFirstName(user.getFirstName());
		this.userDto.setLastName(user.getLastName());
		this.userDto.setMobile(user.getMobile());
		this.userDto.setMother(user.getMother());
		this.userDto.setPan(user.getPan());
		this.userDto.setState(user.getState());
		this.userDto.setStatus(user.getStatus());
		this.userDto.setTown(user.getTown());
		this.userDto.setVoterIdCard(user.getVoterIdCard());
	}
}






