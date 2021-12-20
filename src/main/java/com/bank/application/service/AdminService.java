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
public class AdminService {

	@Autowired
	private UserRepository userRepository;
	
	public List<UserDto> getAllUser(String email){
		List<UserDto> userList = new ArrayList<UserDto>();
		
		userRepository.adminDashboardUser(email).forEach(item -> {
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
