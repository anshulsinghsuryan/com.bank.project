package com.bank.application.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.bank.application.dto.registerdto.Step1;
import com.bank.application.dto.registerdto.Step2;
import com.bank.application.dto.registerdto.Step3;
import com.bank.application.dto.registerdto.Step4;
import com.bank.application.dto.registerdto.Step5;
import com.bank.application.dto.registerdto.Step6;
import com.bank.application.enums.FileConstants;
import com.bank.application.service.RegisterService;
import com.bank.application.utility.FileUtility;
import com.bank.application.utility.ValidateFile;

@Controller
public class UserController {

	@Autowired
	private RegisterService registerService;

	@RequestMapping("/")
	public String home() {
		return "home";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("step1", new Step1());
		return "registration/step1";
	}

	@PostMapping("/register/step1")
	public String stepFirst(@Valid @ModelAttribute("step1") Step1 step1, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("step1", step1);
			return "registration/step1";
		}
		registerService.converStep1ToEntity(step1);
		model.addAttribute("step2", new Step2());
		return "registration/step2";
	}

	@PostMapping("/register/step2")
	public String stepSecond(@Valid @ModelAttribute("step2") Step2 step2, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("step2", step2);
			return "registration/step2";
		}
		registerService.converStep2ToEntity(step2);
		model.addAttribute("step3", new Step3());
		return "registration/step3";
	}

	@PostMapping("/register/step3")
	public String stepThird(@Valid @ModelAttribute("step3") Step3 step3, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("step3", step3);
			return "registration/step3";
		}
		registerService.converStep3ToEntity(step3);
		model.addAttribute("step4", new Step4());
		return "registration/step4";
	}

	@PostMapping("/register/step4")
	public String stepFourth(@Valid @ModelAttribute("step4") Step4 step4, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("step4", step4);
			return "registration/step4";
		}
		registerService.converStep4ToEntity(step4);
		model.addAttribute("step5", new Step5());
		return "registration/step5";
	}

	@PostMapping("/register/step5")
	public String stepFifth(@Valid @ModelAttribute("step5") Step5 step5, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("step5", step5);
			return "registration/step5";
		}
		registerService.converStep5ToEntity(step5);
		model.addAttribute("step6", new Step6());
		return "registration/step6";
	}

	@PostMapping("/register/step6")
	public String stepSixth(@RequestAttribute("photo") MultipartFile photo,
			@RequestAttribute("adhar") MultipartFile adhar, @RequestAttribute("pan") MultipartFile pan,
			@RequestAttribute("signature") MultipartFile signature,
			@RequestAttribute("voterIdCard") MultipartFile voterIdCard, Model model) {

		if (photo.isEmpty() || adhar.isEmpty() || pan.isEmpty() || signature.isEmpty() || voterIdCard.isEmpty()) {
			return "registration/step6";
		} else {
			Step6 step6 = new Step6();
			if (ValidateFile.isFileNameValid(photo.getOriginalFilename())
					&& ValidateFile.isSizeValid(photo.getSize())) {

				String fileName = FileUtility.saveFile(photo, FileConstants.PROFILE_PATH.getStrValue(),
						photo.getOriginalFilename());
				
				step6.setPhoto(fileName);
			} else {
				return "registration/step6";
			}
			if (ValidateFile.isFileNameValid(adhar.getOriginalFilename())
					&& ValidateFile.isSizeValid(adhar.getSize())) {

				String fileName = FileUtility.saveFile(adhar, FileConstants.ADHAR_PATH.getStrValue(),
						adhar.getOriginalFilename());
				step6.setAdhar(fileName);
			} else {
				return "registration/step6";
			}
			if (ValidateFile.isFileNameValid(pan.getOriginalFilename())
					&& ValidateFile.isSizeValid(pan.getSize())) {

				String fileName = FileUtility.saveFile(pan, FileConstants.PAN_PATH.getStrValue(),
						pan.getOriginalFilename());
				step6.setPan(fileName);
			} else {
				return "registration/step6";
			}
			if (ValidateFile.isFileNameValid(signature.getOriginalFilename())
					&& ValidateFile.isSizeValid(signature.getSize())) {

				String fileName = FileUtility.saveFile(signature, FileConstants.SIGNATURE_PATH.getStrValue(),
						signature.getOriginalFilename());
				step6.setSignature(fileName);
			} else {
				return "registration/step6";
			}
			if (ValidateFile.isFileNameValid(voterIdCard.getOriginalFilename())
					&& ValidateFile.isSizeValid(voterIdCard.getSize())) {

				String fileName = FileUtility.saveFile(voterIdCard, FileConstants.VOTERIDCARD_PATH.getStrValue(),
						voterIdCard.getOriginalFilename());
				step6.setVoterIdCard(fileName);
			} else {
				return "registration/step6";
			}
			
			registerService.converStep6ToEntity(step6);
			registerService.save();
		}
		return "redirect:/";
	}

}
