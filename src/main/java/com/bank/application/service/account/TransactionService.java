package com.bank.application.service.account;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bank.application.dto.Account;
import com.bank.application.dto.account.TransactionDetails;
import com.bank.application.entity.User;
import com.bank.application.enums.ApiConstants;
import com.bank.application.repository.UserRepository;
import com.bank.application.utility.CommonUtils;

@Service
public class TransactionService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);
	
	
	@Autowired
	private UserRepository userRepository;
	
	public List<TransactionDetails> getTransactionDetails(String email, String token){
		User user = userRepository.findUserByEmail(email);
		Account account = getAccountDetails(user.getId(),token);
		
		RestTemplate restTemplate = new RestTemplate();
		StringBuilder urlAddress = new StringBuilder();

		urlAddress.append(ApiConstants.ACCOUNT_SERVER_ADDRESS.getStrValue());
		urlAddress.append(ApiConstants.ACCOUNT.getStrValue());
		urlAddress.append(ApiConstants.SLASH.getStrValue());
		urlAddress.append("transaction");
		urlAddress.append(ApiConstants.SLASH.getStrValue());
		urlAddress.append(account.getAccount());
		List<TransactionDetails> transactionList = null;
		
		try {

			HttpHeaders headers = new HttpHeaders();

			HttpEntity<Object> request = new HttpEntity<>(headers);

			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("Authorization", token);

			ResponseEntity<List<TransactionDetails>> accountResponse = restTemplate.exchange(urlAddress.toString(), HttpMethod.GET,
					request, new ParameterizedTypeReference<List<TransactionDetails>>() {});
			
			if (accountResponse.getStatusCode() == HttpStatus.FOUND) {
				transactionList = accountResponse.getBody();			
			} else {
				LOGGER.error("Details Not Found !!");
			}

		} catch (Exception e) {
			LOGGER.error("Something went wrong while fetching Transaction details !! {}",CommonUtils.getLogMessage(e));
		}	
		
		return transactionList;
	}
	
	private Account getAccountDetails(Long id, String token) {
		Account account = null;
		
		RestTemplate restTemplate = new RestTemplate();
		StringBuilder urlAddress = new StringBuilder();

		urlAddress.append(ApiConstants.ACCOUNT_SERVER_ADDRESS.getStrValue());
		urlAddress.append(ApiConstants.ACCOUNT.getStrValue());
		urlAddress.append(ApiConstants.SLASH.getStrValue());
		urlAddress.append("details");
		urlAddress.append(ApiConstants.SLASH.getStrValue());
		urlAddress.append(id);

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
	
	
	public TransactionDetails getTransactionDetailsById(Long id, String token) {
		RestTemplate restTemplate = new RestTemplate();
		StringBuilder urlAddress = new StringBuilder();

		urlAddress.append(ApiConstants.ACCOUNT_SERVER_ADDRESS.getStrValue());
		urlAddress.append(ApiConstants.ACCOUNT.getStrValue());
		urlAddress.append(ApiConstants.SLASH.getStrValue());
		urlAddress.append("transactionbyid");
		urlAddress.append(ApiConstants.SLASH.getStrValue());
		urlAddress.append(id);
		TransactionDetails transaction = null;
		
		try {

			HttpHeaders headers = new HttpHeaders();

			HttpEntity<Object> request = new HttpEntity<>(headers);

			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("Authorization", token);

			ResponseEntity<TransactionDetails> accountResponse = restTemplate.exchange(urlAddress.toString(), HttpMethod.GET,
					request, TransactionDetails.class);
			
			if (accountResponse.getStatusCode() == HttpStatus.FOUND) {
				transaction = accountResponse.getBody();			
			} else {
				LOGGER.error("Details Not Found !!");
			}

		} catch (Exception e) {
			LOGGER.error("Something went wrong while fetching Transaction details !! {}",CommonUtils.getLogMessage(e));
		}	
		
		return transaction;
	}
}
