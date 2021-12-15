package com.bank.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.application.dto.registerdto.Step1;
import com.bank.application.dto.registerdto.Step2;
import com.bank.application.dto.registerdto.Step3;
import com.bank.application.dto.registerdto.Step4;
import com.bank.application.dto.registerdto.Step5;
import com.bank.application.dto.registerdto.Step6;
import com.bank.application.entity.User;
import com.bank.application.repository.UserRepository;

@Service
public class RegisterService {

	@Autowired
	private UserRepository userRepository;
	
	private User user = new User();
	
	public void converStep1ToEntity(Step1 step1) {
		user.setFirstName(step1.getFirstName());
		user.setLastName(step1.getLastName());
	}
	public void converStep2ToEntity(Step2 step2) {
		user.setFather(step2.getFatherName());
		user.setMother(step2.getMotherName());
	}
	public void converStep3ToEntity(Step3 step3) {
		user.setEmail(step3.getEmail());
		user.setMobile(step3.getMobile());
	}
	public void converStep4ToEntity(Step4 step4) {
		user.setDateOfBirth(step4.getDateOfBirth());
		user.setAdhar(step4.getAdhar());
		user.setPan(step4.getPan());
		user.setVoterIdCard(step4.getVoterIdCard());
	}
	public void converStep5ToEntity(Step5 step5) {
		user.setAddressLine1(step5.getAddressLine1());
		user.setAddressLine2(step5.getAddressLine2());
		user.setAreaCode(step5.getAreaCode());
		user.setTown(step5.getTown());
		user.setDistrict(step5.getDistrict());
		user.setState(step5.getState());
		user.setCountry(step5.getCountry());
	}
	public void converStep6ToEntity(Step6 step6) {
		user.setProfilePicture(step6.getPhoto());
		user.setPanDocument(step6.getPan());
		user.setVoterIdCardDocument(step6.getVoterIdCard());
		user.setAdharDocument(step6.getAdhar());
		user.setSignaturePicture(step6.getSignature());
	}
	
	public void save() {
		userRepository.save(user);
	}
}
