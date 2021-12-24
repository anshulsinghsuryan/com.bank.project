package com.bank.application.service.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bank.application.dto.Account;
import com.bank.application.entity.User;
import com.bank.application.enums.ApiConstants;
import com.bank.application.repository.UserRepository;
import com.bank.application.utility.CommonUtils;



@Service
public class AccountDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountDetailsService.class);
	
	@Value("${com.bank.account.server}")
	private String address;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public Account getAccountDetails(String username,String token) {
		
		Account account = null;
		
		RestTemplate restTemplate = new RestTemplate();
		StringBuilder urlAddress = new StringBuilder();

		urlAddress.append(address);
		urlAddress.append(ApiConstants.ACCOUNT.getStrValue());
		urlAddress.append(ApiConstants.SLASH.getStrValue());
		urlAddress.append("details");
		urlAddress.append(ApiConstants.SLASH.getStrValue());
		
		User user = userRepository.findUserByEmail(username);
		
		urlAddress.append(user.getId());

		try {

			HttpHeaders headers = new HttpHeaders();

			HttpEntity<Object> request = new HttpEntity<>(headers);

			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("Authorization", token);

			ResponseEntity<Account> accountResponse = restTemplate.exchange(urlAddress.toString(), HttpMethod.GET,
					request, Account.class);

			if (accountResponse.getStatusCode() == HttpStatus.FOUND) {
				account = accountResponse.getBody();			
			} else {
				LOGGER.error("Account Not Found !!");
			}

		} catch (Exception e) {
			LOGGER.error("Something went wrong while fetching account details !! {}",CommonUtils.getLogMessage(e));
		}	
		return account;

	}
}
