package com.bank.application.service.account;

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

import com.bank.application.dto.Account;
import com.bank.application.dto.account.MoneyTransferDto;
import com.bank.application.entity.User;
import com.bank.application.enums.ApiConstants;
import com.bank.application.repository.UserRepository;
import com.bank.application.utility.CommonUtils;

@Service
public class MoneyTransferService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MoneyTransferService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	private Account currentUserAccount;
	
	public void transferMoney(MoneyTransferDto moneyTransferDto, String email, String token) {
		
		User user = userRepository.findUserByEmail(email);	
		currentUserAccount  = getAccoutDetails(user.getId(),token);
		
		if(debitMoney(moneyTransferDto.getAmmount(),token)) {
			if(!depositMoney(moneyTransferDto,token)) {
				moneyTransferDto.setAccount(currentUserAccount.getAccount());
				depositMoney(moneyTransferDto,token);
			}
		}
	}
	
	private boolean debitMoney(Double amount, String token) {
		RestTemplate restTemplate = new RestTemplate();
		StringBuilder urlAddress = new StringBuilder();

		urlAddress.append(ApiConstants.ACCOUNT_SERVER_ADDRESS.getStrValue());
		urlAddress.append(ApiConstants.ACCOUNT.getStrValue());
		urlAddress.append(ApiConstants.SLASH.getStrValue());
		urlAddress.append("debit");
		
		if(currentUserAccount.getBalance() < amount) {
			return false;
		}
		MoneyTransferDto moneyTransferDto = new MoneyTransferDto();
		moneyTransferDto.setAmmount(amount);
		moneyTransferDto.setAccount(currentUserAccount.getAccount());
		try {

			HttpHeaders headers = new HttpHeaders();

			HttpEntity<MoneyTransferDto> request = new HttpEntity<MoneyTransferDto>(moneyTransferDto,headers);

			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("Authorization", token);

			ResponseEntity<String> accountResponse = restTemplate.exchange(urlAddress.toString(), HttpMethod.PUT,
					request, String.class);

			if (accountResponse.getStatusCode() == HttpStatus.OK) {
				LOGGER.error(accountResponse.getBody());
				return true;			
			} else {
				LOGGER.error("Account Not Found !!");
			}

		} catch (Exception e) {
			LOGGER.error("Something went wrong while debit money from user account !! {}",CommonUtils.getLogMessage(e));
		}	
		return false;
	}
	
	private boolean depositMoney(MoneyTransferDto moneyTransferDto, String token) {
		RestTemplate restTemplate = new RestTemplate();
		StringBuilder urlAddress = new StringBuilder();

		urlAddress.append(ApiConstants.ACCOUNT_SERVER_ADDRESS.getStrValue());
		urlAddress.append(ApiConstants.ACCOUNT.getStrValue());
		urlAddress.append(ApiConstants.SLASH.getStrValue());
		urlAddress.append("deposit");

		try {

			HttpHeaders headers = new HttpHeaders();

			HttpEntity<MoneyTransferDto> request = new HttpEntity<MoneyTransferDto>(moneyTransferDto,headers);

			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("Authorization", token);

			ResponseEntity<String> accountResponse = restTemplate.exchange(urlAddress.toString(), HttpMethod.PUT,
					request, String.class);

			if (accountResponse.getStatusCode() == HttpStatus.OK) {
				LOGGER.error(accountResponse.getBody());
				return true;			
			} else {
				LOGGER.error("Account Not Found !!");
			}

		} catch (Exception e) {
			LOGGER.error("Something went wrong while deposit money in user account !! {}",CommonUtils.getLogMessage(e));
		}	
		return false;
	}
	
	private Account getAccoutDetails(Long id, String token) {
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
}
