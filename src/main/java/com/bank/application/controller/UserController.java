package com.bank.application.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bank.application.dto.ResetPasswordDto1;
import com.bank.application.dto.ResetPasswordDto2;
import com.bank.application.dto.UserDto;
import com.bank.application.dto.registerdto.Step1;
import com.bank.application.dto.registerdto.Step2;
import com.bank.application.dto.registerdto.Step3;
import com.bank.application.dto.registerdto.Step4;
import com.bank.application.dto.registerdto.Step5;
import com.bank.application.dto.registerdto.Step6;
import com.bank.application.email.EmailSender;
import com.bank.application.entity.User;
import com.bank.application.enums.Constants;
import com.bank.application.enums.FileConstants;
import com.bank.application.service.OtpService;
import com.bank.application.service.PasswordResetService;
import com.bank.application.service.RegisterService;
import com.bank.application.service.UserService;
import com.bank.application.utility.FileUtility;
import com.bank.application.utility.MessageService;
import com.bank.application.utility.ValidateFile;

@Controller
public class UserController {

	@Autowired
	private RegisterService registerService;
	
	@Autowired
	private MessageService message;
	
	@Autowired
	private OtpService otpService;

	@Autowired
	private EmailSender emailSender;
	
	@Autowired
	private PasswordResetService passwordResetService;
	
	@Autowired
	private UserService userService;
	
	private String resetPasswordEmail;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}

	@GetMapping("/register")
	public String register(Model model,Principal principal,HttpSession session) {
		if(principal == null) {
			model.addAttribute("step1", new Step1());
			return "registration/step1";
		}
		else {
			message.setType("danger");
			message.setBody("Please log out first to continue with registeration process !!");
			session.setAttribute("message", message);
			return "redirect:/";
		}
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
	public String stepThird(@Valid @ModelAttribute("step3") Step3 step3, BindingResult result, Model model, HttpSession session) {
		if (result.hasErrors()) {
			model.addAttribute("step3", step3);
			return "registration/step3";
		}
		if(step3.getPassword().equals(step3.getConfirmPassword())) {
			emailSender.sendEmail(step3.getEmail(), "Axis Bank Verification",
					"<p>Welcome to Axis Bank Your verification is panding,</p>"
					+ "<p>please complete your verification to continue registration process !!</p1><br>"
					+ "<h2>Your OTP is : %s</h2>".formatted(otpService.getOTP()));
			registerService.converStep3ToEntity(step3);
			model.addAttribute("step4", new Step4());
			message.setType("success");
			message.setBody("An email with otp is sent to your email address !!");
			session.setAttribute("message", message);
			return "registration/step4";
		} else {
			message.setType("danger");
			message.setBody("Password is not equal to Confirm Password !!");
			session.setAttribute("message", message);
			return "registration/step3";
		}
	}

	@PostMapping("/register/step4")
	public String stepFourth(@Valid @ModelAttribute("step4") Step4 step4, BindingResult result, Model model, HttpSession session) {
		if (result.hasErrors()) {
			model.addAttribute("step4", step4);
			return "registration/step4";
		}
		if(otpService.verifyOTP(step4.getOtp())) {
			registerService.converStep4ToEntity(step4);
			model.addAttribute("step5", new Step5());
			message.setType("success");
			message.setBody("Congratulations, your email has been verified !!");
			session.setAttribute("message", message);
			return "registration/step5";
		}
		message.setType("danger");
		message.setBody("OTP not verified, Please try again !!");
		session.setAttribute("message", message);
		model.addAttribute("step4", step4);
		return "registration/step4";
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
			@RequestAttribute("voterIdCard") MultipartFile voterIdCard, Model model,HttpSession session) {

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
				message.setType("danger");
				message.setBody("Please check file size and type, max 5MB and jpg, png, pdf files are allowed !!");
				session.setAttribute("message", message);
				return "registration/step6";
			}
			if (ValidateFile.isFileNameValid(adhar.getOriginalFilename())
					&& ValidateFile.isSizeValid(adhar.getSize())) {

				String fileName = FileUtility.saveFile(adhar, FileConstants.ADHAR_PATH.getStrValue(),
						adhar.getOriginalFilename());
				step6.setAdhar(fileName);
			} else {
				message.setType("danger");
				message.setBody("Please check file size and type, max 5MB and jpg, png, pdf files are allowed !!");
				session.setAttribute("message", message);
				return "registration/step6";
			}
			if (ValidateFile.isFileNameValid(pan.getOriginalFilename())
					&& ValidateFile.isSizeValid(pan.getSize())) {

				String fileName = FileUtility.saveFile(pan, FileConstants.PAN_PATH.getStrValue(),
						pan.getOriginalFilename());
				step6.setPan(fileName);
			} else {
				message.setType("danger");
				message.setBody("Please check file size and type, max 5MB and jpg, png, pdf files are allowed !!");
				session.setAttribute("message", message);
				return "registration/step6";
			}
			if (ValidateFile.isFileNameValid(signature.getOriginalFilename())
					&& ValidateFile.isSizeValid(signature.getSize())) {

				String fileName = FileUtility.saveFile(signature, FileConstants.SIGNATURE_PATH.getStrValue(),
						signature.getOriginalFilename());
				step6.setSignature(fileName);
			} else {
				message.setType("danger");
				message.setBody("Please check file size and type, max 5MB and jpg, png, pdf files are allowed !!");
				session.setAttribute("message", message);
				return "registration/step6";
			}
			if (ValidateFile.isFileNameValid(voterIdCard.getOriginalFilename())
					&& ValidateFile.isSizeValid(voterIdCard.getSize())) {

				String fileName = FileUtility.saveFile(voterIdCard, FileConstants.VOTERIDCARD_PATH.getStrValue(),
						voterIdCard.getOriginalFilename());
				step6.setVoterIdCard(fileName);
			} else {
				message.setType("danger");
				message.setBody("Please check file size and type, max 5MB and jpg, png, pdf files are allowed !!");
				session.setAttribute("message", message);
				return "registration/step6";
			}
			
			registerService.converStep6ToEntity(step6);
			registerService.save();
		}
		message.setType("success");
		message.setBody("Congratulations, your application is submitted !!");
		session.setAttribute("message", message);
		return "redirect:/";
	}
	@RequestMapping("/login")
	public String login(Principal principal, HttpSession session) {
		if(principal == null)
			return "login";
		else {
			message.setType("danger");
			message.setBody("Please logout first !!");
			session.setAttribute("message", message);
			return "redirect:/";
		}
		
	}
	
	@RequestMapping("/login/failure")
	public String loginFailure(HttpSession session) {
		message.setType("danger");
		message.setBody("Login failed, Please check your email and password !!");
		session.setAttribute("message", message);
		return "redirect:/login";
	}
	
	@RequestMapping("/reset/password")
	public String intiatePasswordReset(Model model) {
		model.addAttribute("reset", new ResetPasswordDto1());
		return "reset-password";
	}
	
	@RequestMapping(value="/reset/password",method=RequestMethod.POST)
	public String intiatePasswordReset(@Valid @ModelAttribute("reset") ResetPasswordDto1 resetPasswordDto, BindingResult result,Model model,HttpSession session) {
		if(result.hasErrors()) {
			model.addAttribute("reset", resetPasswordDto);
			return "reset-password";
		}
		emailSender.sendEmail(resetPasswordDto.getEmail(), "Axis Bank Verification",
				"<p>Thanks for using Axis Bank Your verification is panding,</p>"
				+ "<p>please complete your verification to continue password reset process !!</p1><br>"
				+ "<h2>Your OTP is : %s</h2>".formatted(otpService.getOTP()));
		session.setAttribute("email", resetPasswordDto.getEmail());
		return "otpverification";
	}
	
	@RequestMapping(value="/reset/verify/otp",method=RequestMethod.POST)
	public String verifyOTP(@RequestParam("otp") String otp, Model model, HttpSession session) {
		if(otpService.verifyOTP(otp)) {
			message.setType("success");
			message.setBody("OTP verifaication successfull !!");
			session.setAttribute("message", message);
			model.addAttribute("reset", new ResetPasswordDto2());
			this.resetPasswordEmail = (String) session.getAttribute("email");
			return "reset-page";
		}
		message.setType("danger");
		message.setBody("OTP verifaication failed, Please check your OTP !!");
		session.setAttribute("message", message);
		return "otpverification";
	}
	
	@RequestMapping(value="/reset/verify/password",method=RequestMethod.POST)
	public String resetPassword(@Valid @ModelAttribute("reset") ResetPasswordDto2 resetDto,BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			model.addAttribute("reset", resetDto);
			return "reset-page"; 
		}
		if(resetDto.getPassword().equals(resetDto.getConfirmPassword())) {
			passwordResetService.resetPassword(resetDto, this.resetPasswordEmail);
			message.setType("success");
			message.setBody("Password reset successfull !!");
			session.setAttribute("message", message);
			return "redirect:/login";
		}
		model.addAttribute("reset", resetDto);
		message.setType("danger");
		message.setBody("Password in not equal to confirm password !!");
		session.setAttribute("message", message);
		return "reset-page";
	}
	
	@RequestMapping("/user/dashboard")
	public String userDashboard(Principal principal, Model model) {
		UserDto user = userService.getUserStatus(principal.getName());
		model.addAttribute("user", user);
		if(user.getStatus().equals(Constants.APPROVED.toString())) {
			return "redirect:/account/details";
		}
		return "user-dashboard";
	}
}
