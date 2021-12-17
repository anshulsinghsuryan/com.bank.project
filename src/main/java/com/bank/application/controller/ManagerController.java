package com.bank.application.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bank.application.enums.ApiConstants;
import com.bank.application.enums.Constants;
import com.bank.application.service.ManagerService;

@Controller
public class ManagerController {

	@Autowired
	private ManagerService managerService;

	@RequestMapping("/manager")
	public String managerDashboard(Model model) {
		model.addAttribute("users", managerService.getUsers());
		Map<String, String> map = new HashMap<String, String>();
		map.put(Constants.PENDING.toString(), ApiConstants.VIEW_ALL_PENDING.getStrValue());
		map.put(Constants.APPROVED.toString(), ApiConstants.VIEW_ALL_APPROVED.getStrValue());
		map.put(Constants.INREVIEW.toString(), ApiConstants.VIEW_ALL_INREVIEW.getStrValue());
		map.put(Constants.REJECTED.toString(), ApiConstants.VIEW_ALL_REJECTED.getStrValue());
		model.addAttribute("map", map);
		return "manager/manager-dashboard";
	}

	@RequestMapping("/manager/{status}/{id}")
	public String manager_dashboard_service(@PathVariable("status") String status,@PathVariable("id") Long id, Model model) {
		if(status.equals("approve")) {
			managerService.approve(id);
		} else if (status.equals("reject")) {
			managerService.reject(id);
		} else if(status.equals("inreview")) {
			managerService.inreview(id);
		} else if(status.equals("pending")) {
			managerService.pending(id);
		}	
		return "redirect:/manager";
	}
	
	@RequestMapping("/manager/{request}/{status}/{id}")
	public String manager_status_dashboard_service(@PathVariable("request") String request, @PathVariable("status") String status, @PathVariable("id") Long id, Model model) {
		if(status.equals("approve")) {
			managerService.approve(id);
		} else if (status.equals("reject")) {
			managerService.reject(id);
		} else if(status.equals("inreview")) {
			managerService.inreview(id);
		} else if(status.equals("pending")) {
			managerService.pending(id);
		}	
		return "redirect:/manager/"+request;
	}
	
	@RequestMapping("/manager/{request}")
	public String viewAllInReview(@PathVariable("request") String request, Model model) {
		
		if(request.equals("view-all-inreview")) {
			model.addAttribute("users", managerService.getInreviewUser());
		} else if (request.equals("view-all-pending")) {
			model.addAttribute("users", managerService.getPendingUser());
		} else if (request.equals("view-all-rejected")) {
			model.addAttribute("users", managerService.getRejectedUser());
		} else if (request.equals("view-all-approved")) {
			model.addAttribute("users", managerService.getApprovedUser());
		}	
		Map<String, String> map = new HashMap<String, String>();
		map.put(Constants.PENDING.toString(), ApiConstants.VIEW_ALL_PENDING.getStrValue());
		map.put(Constants.APPROVED.toString(), ApiConstants.VIEW_ALL_APPROVED.getStrValue());
		map.put(Constants.INREVIEW.toString(), ApiConstants.VIEW_ALL_INREVIEW.getStrValue());
		map.put(Constants.REJECTED.toString(), ApiConstants.VIEW_ALL_REJECTED.getStrValue());
		model.addAttribute("map", map);
		model.addAttribute("request", request);
		return "manager/status-dashboard";
	}
}
