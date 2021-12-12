package com.bank.project.service;

import java.util.Random;
import java.util.Scanner;

import com.bank.project.dao.BankDao;
import com.bank.project.entity.BankEntity;

public class BankService {

	public static void registerUser() {
		Scanner sc = new Scanner(System.in);

		BankEntity bankEntity = new BankEntity();

		System.out.println("Enter Username");
		String username = sc.next();
		bankEntity.setUsername(username);
		System.out.println("Enter Password");
		String password = sc.next();
		bankEntity.setPassword(password);
		System.out.println("Enter Amount");
		int amount = sc.nextInt();
		bankEntity.setAmount(amount);
		System.out.println("Enter Date Of Birth");
		String dob = sc.next();
		bankEntity.setDob(dob);
		StringBuilder builder = new StringBuilder();
		
		Random random = new Random(90);
		builder.append(random.nextInt(99));
		for(int i=0;i<5;i++) {
			builder.append(i+random.nextInt(9+i));
		}
		bankEntity.setAccount(""+builder);
		sc.close();
		BankDao.register(bankEntity);
	}
	
	
	public static void getBalance() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Account Number");
		String account = sc.next();
		System.out.println("Balance is : "+BankDao.getAmount(account));
		sc.close();
		
	}
	
	public static void debitAmount() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Account Number");
		String account = sc.next();
		System.out.println("Enter Amount To Debit");
		int amount = sc.nextInt();
		BankDao.debit(account,amount);
		sc.close();
		
	}
	
	public static void depositAmount() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Account Number");
		String account = sc.next();
		System.out.println("Enter Amount To Deposit");
		int amount = sc.nextInt();
		BankDao.diposit(account,amount);
		sc.close();
		
	}
	
}
