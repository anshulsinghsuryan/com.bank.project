package com.bank.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bank.application.service.ManagerService;

@Controller
public class ManagerController {

	@Autowired
	private ManagerService managerService;
	
	@RequestMapping("/manager")
	public String managerDashboard(Model model) {
		model.addAttribute("users", managerService.getUsers());
		return "manager/manager-dashboard";
	}
	
	@RequestMapping("/manager/approve/{id}")
	public String approve(@PathVariable("id") Long id) {
		managerService.approve(id);
		return "redirect:/manager";
	}
	
	@RequestMapping("/manager/reject/{id}")
	public String reject(@PathVariable("id") Long id) {
		managerService.reject(id);
		return "redirect:/manager";
	}
	
	@RequestMapping("/manager/inreview/{id}")
	public String inreview(@PathVariable("id") Long id) {
		managerService.inreview(id);
		return "redirect:/manager";
	}
	
	@RequestMapping("/manager/pending/{id}")
	public String pending(@PathVariable("id") Long id) {
		managerService.pending(id);
		return "redirect:/manager";
	}
}
