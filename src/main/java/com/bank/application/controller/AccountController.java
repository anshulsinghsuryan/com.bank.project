package com.bank.application.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bank.application.dto.Account;
import com.bank.application.dto.account.MoneyTransferDto;
import com.bank.application.dto.account.TransactionDetails;
import com.bank.application.enums.ApiConstants;
import com.bank.application.enums.Constants;
import com.bank.application.service.account.AccountDetailsService;
import com.bank.application.service.account.MoneyTransferService;
import com.bank.application.service.account.TransactionService;
import com.bank.application.utility.CommonUtils;
import com.bank.application.utility.Exporter;
import com.bank.application.utility.Token;

@Controller
public class AccountController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private AccountDetailsService accountDetailsService;
	
	@Autowired
	private MoneyTransferService moneyTransferService;
	
	@Autowired
	private TransactionService transactionService;
	
	@RequestMapping("/account/details")
	public String getAccountDetails(Principal principal,Model model, HttpSession session) {
		
		String token = (String) session.getAttribute("token");
		if(token == null) {
			return "redirect:/account/verification";
		}
		Account account = accountDetailsService.getAccountDetails(principal.getName(), token);
		model.addAttribute("account", account);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put(Constants.TRANSACTIONS.toString(), ApiConstants.TRANSACTIONS.getStrValue());
		map.put(Constants.DASHBOARD.toString(), ApiConstants.DASHBOARD.getStrValue());
		model.addAttribute("map", map);
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
	
	@RequestMapping("/account/transaction/details")
	public String transactionService(Principal principal, HttpSession session,Model model) {
		String token = (String) session.getAttribute("token");
		if(token == null) {
			return "redirect:/account/verification";
		}
		List<TransactionDetails> trlist = transactionService.getTransactionDetails(principal.getName(), token);
		model.addAttribute("transactions", trlist);
		return "transaction-details";
	}
	
	@RequestMapping("/account/transaction/details/{id}")
	public String transactionServiceById(@PathVariable("id") Long id, HttpSession session,Model model) {
		String token = (String) session.getAttribute("token");
		if(token == null) {
			return "redirect:/account/verification";
		}
		TransactionDetails transaction = transactionService.getTransactionDetailsById(id, token);
		model.addAttribute("transaction", transaction);
		return "transaction-detail";
	}
	
	@RequestMapping("/account/transaction/details/download/{id}")
	public void exportToPDF(@PathVariable("id") Long id, HttpSession session,HttpServletResponse response){
		String token = (String) session.getAttribute("token");
		try {
			response.setContentType("application/pdf");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=transaction_slip_" + currentDateTime + ".pdf";
	        response.setHeader(headerKey, headerValue);
	         
	        TransactionDetails transaction = transactionService.getTransactionDetailsById(id, token);
	         
	        Exporter exporter = new Exporter(transaction);
	        exporter.export(response);  
	        
		} catch (Exception e) {
			LOGGER.error("Error : error in exporting data into pdf !! {}",CommonUtils.getLogMessage(e));
		}
    }
	
}
