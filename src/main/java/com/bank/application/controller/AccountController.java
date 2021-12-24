package com.bank.application.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bank.application.dto.Account;
import com.bank.application.dto.account.MoneyTransferDto;
import com.bank.application.service.account.AccountDetailsService;
import com.bank.application.service.account.MoneyTransferService;
import com.bank.application.utility.Token;

@Controller
public class AccountController {

	@Autowired
	private AccountDetailsService accountDetailsService;
	
	@Autowired
	private MoneyTransferService moneyTransferService;
	
	@RequestMapping("/account/details")
	public String getAccountDetails(Principal principal,Model model, HttpSession session) {
		
		String token = (String) session.getAttribute("token");
		if(token == null) {
			return "redirect:/account/verification";
		}
		Account account = accountDetailsService.getAccountDetails(principal.getName(), token);
		model.addAttribute("account", account);
		return "account";
	}
	
	@RequestMapping("/account/verification")
	public String verify_user(HttpSession session) {
		session.removeAttribute("token");
		return "account-verify";
	}
	
	@RequestMapping(value="/account/verify",method=RequestMethod.POST)
	public String verify_user(@RequestParam("password") String password,Principal principal,HttpSession session) {
		String token = Token.getToken(principal.getName(), password);
		if(token == null) {
			return "redirect:/account/verification";
		}
		session.setAttribute("token", token);
		return "redirect:/account/details";
	}
	
	@RequestMapping("/account/transfer")
	public String transferMoney() {
		return "transfer-money";
	}
	
	@RequestMapping(value="/account/transfer",method=RequestMethod.POST)
	public String transferMoney(@ModelAttribute MoneyTransferDto moneyTransferDto, Principal principal, HttpSession session) {
		String token = (String) session.getAttribute("token");
		if(token == null) {
			return "redirect:/account/verification";
		}
		moneyTransferService.transferMoney(moneyTransferDto, principal.getName(), token);
		return "redirect:/account/details";
	}
}
