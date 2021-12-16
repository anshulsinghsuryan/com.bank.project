package com.bank.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bank.application.service.AdminService;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@RequestMapping("/admin")
	public String portal(Model model) {
		model.addAttribute("users", adminService.getAllUser());
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
}
