package com.bank.application.utility;

import org.springframework.stereotype.Component;

@Component
public class MessageService {

	private String type;
	private String body;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
}
