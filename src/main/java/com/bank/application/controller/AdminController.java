package com.bank.application.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bank.application.service.AdminService;
import com.bank.application.service.UserService;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired 
	private UserService userService;
	
	@RequestMapping("/admin")
	public String portal(Model model,Principal principal) {
		model.addAttribute("users", adminService.getAllUser(principal.getName()));
		return "admin/admin-dashboard";
	}
	
	@RequestMapping("/admin/apointmanager/{id}")
	public String apointManager(@PathVariable("id") Long id) {
		adminService.apointManager(id);
		return "redirect:/admin";
	}
	@RequestMapping("/admin/apointuser/{id}")
	public String apointUser(@PathVariable("id") Long id) {
		adminService.apointUser(id);
		return "redirect:/admin";
	}
	@RequestMapping("/admin/apointadmin/{id}")
	public String apointAdmin(@PathVariable("id") Long id) {
		adminService.apointAdmin(id);
		return "redirect:/admin";
	}
	
	@RequestMapping("/admin/user/details/{id}")
	public String viewDetails(@PathVariable("id") Long id,Model model) {
		model.addAttribute("user", userService.getUserStatus(id));
		return "user-dashboard";
	}
}
