package com.bank.application.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.bank.application.dto.JwtRequest;
import com.bank.application.dto.JwtResponse;
import com.bank.application.enums.ApiConstants;

public class Token {

	private static final Logger LOGGER = LoggerFactory.getLogger(Token.class);
	
	private static String token = null;
	private String username;
	private String password;
	private Token(String username,String password) {
		this.username = username;
		this.password = password;
	}
	public static String getToken(String username, String password) {
		
		if(token == null) {
			Token tokenReq = new Token(username,password);
			token = tokenReq.sendRequest();
		}
		return token;
	}
	
	private String sendRequest() {
		JwtResponse res = new JwtResponse();
		JwtRequest jwtRequest = new JwtRequest();
		jwtRequest.setUsername(username);
		jwtRequest.setPassword(password);
		
		RestTemplate restTemplate = new RestTemplate();
		StringBuilder urlAddress = new StringBuilder();

		urlAddress.append(ApiConstants.ACCOUNT_SERVER_ADDRESS.getStrValue());
		urlAddress.append(ApiConstants.ACCOUNT.getStrValue());
		urlAddress.append(ApiConstants.SLASH.getStrValue());
		urlAddress.append("token");
		
		HttpHeaders headers = new HttpHeaders();

		HttpEntity<JwtRequest> request = new HttpEntity<JwtRequest>(jwtRequest, headers);
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<JwtResponse> jwtRes = restTemplate.exchange(urlAddress.toString(), HttpMethod.POST,
				request, JwtResponse.class);

		if (jwtRes.getStatusCode() == HttpStatus.CREATED) {
			res = jwtRes.getBody();
		} else {
			LOGGER.error("Unauthorized !!");
		}
		return res.getToken();
	}
}
