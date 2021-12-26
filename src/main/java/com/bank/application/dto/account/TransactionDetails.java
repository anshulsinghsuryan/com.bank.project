package com.bank.application.dto.account;

import java.util.Date;

public class TransactionDetails {

	private Long transactionId;
	private Long account;
	private String status;
	private Double ammount;
	private String value;
	private Date transactionOn;
	
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public Long getAccount() {
		return account;
	}
	public void setAccount(Long account) {
		this.account = account;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Double getAmmount() {
		return ammount;
	}
	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}
	public Date getTransactionOn() {
		return transactionOn;
	}
	public void setTransactionOn(Date transactionOn) {
		this.transactionOn = transactionOn;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
