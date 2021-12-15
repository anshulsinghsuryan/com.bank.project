package com.bank.application.dto.registerdto;

import org.springframework.web.multipart.MultipartFile;

public class Step6 {

	private String photo;
	private String adhar;
	private String pan;
	private String signature;
	private String voterIdCard;
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getAdhar() {
		return adhar;
	}
	public void setAdhar(String adhar) {
		this.adhar = adhar;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getVoterIdCard() {
		return voterIdCard;
	}
	public void setVoterIdCard(String voterIdCard) {
		this.voterIdCard = voterIdCard;
	}
}
