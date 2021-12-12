package com.bank.project.entity;

public class BankEntity {

	private String username;
	private String password;
	private int amount;
	private String dob;
	private String account;
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	@Override
	public String toString() {
		return "BankEntity [username=" + username + ", password=" + password + ", amount=" + amount + ", dob=" + dob
				+ ", account=" + account + "]";
	}
	
}
