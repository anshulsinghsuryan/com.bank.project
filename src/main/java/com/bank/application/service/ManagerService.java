package com.bank.application.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bank.application.dto.UserDto;
import com.bank.application.dto.account.RegisterAccountDto;
import com.bank.application.entity.User;
import com.bank.application.enums.ApiConstants;
import com.bank.application.enums.Constants;
import com.bank.application.repository.UserRepository;
import com.bank.application.utility.CommonUtils;

@Service
public class ManagerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ManagerService.class);
	
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
	
	public void approve(Long id, String token) {
		if(createAccount(id, token)) {
			userRepository.updateStatus(Constants.APPROVED.toString(), id);
		}		
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
	
	private boolean createAccount(Long id,String token) {
		RestTemplate restTemplate = new RestTemplate();
		StringBuilder urlAddress = new StringBuilder();

		urlAddress.append(ApiConstants.ACCOUNT_SERVER_ADDRESS.getStrValue());
		urlAddress.append(ApiConstants.ACCOUNT.getStrValue());
		urlAddress.append(ApiConstants.SLASH.getStrValue());
		urlAddress.append("create");
		
		User user = userRepository.findById(id).get();
		RegisterAccountDto registerAccountDto = new RegisterAccountDto();
		registerAccountDto.setFirstName(user.getFirstName());
		registerAccountDto.setLastName(user.getLastName());
		registerAccountDto.setUserId(id);

		try {

			HttpHeaders headers = new HttpHeaders();

			HttpEntity<RegisterAccountDto> request = new HttpEntity<RegisterAccountDto>(registerAccountDto,headers);

			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("Authorization", token);

			ResponseEntity<String> accountResponse = restTemplate.exchange(urlAddress.toString(), HttpMethod.POST,
					request, String.class);

			if (accountResponse.getStatusCode() == HttpStatus.CREATED) {
				return true;			
			} else if (accountResponse.getStatusCode() == HttpStatus.NOT_ACCEPTABLE) {
				return true;			
			} else {
				LOGGER.error("Parameters Not Acceptable !!");
			}

		} catch (Exception e) {
			LOGGER.error("Something went wrong while creating user account !! {}", CommonUtils.getLogMessage(e));
		}	
		return false;
	}
}
