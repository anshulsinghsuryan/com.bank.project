package com.bank.project;

import com.bank.project.service.BankService;

public class Application {

	public static void main(String[] args) {
		BankService.registerUser();
		//BankService.getBalance();
		//BankService.depositAmount();
		//BankService.debitAmount();
	}
}
