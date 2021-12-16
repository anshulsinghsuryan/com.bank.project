package com.bank.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private long id;
	
	@Column(name="FIRSTNAME",nullable=false)
	private String firstName;
	
	@Column(name="LASTNAME",nullable=false)
	private String lastName;
	
	@Column(name="FATHER",nullable=false)
	private String father;
	
	@Column(name="MOTHER",nullable=false)
	private String mother;
	
	@Column(name="MOBILE",nullable=false)
	private String mobile;
	
	@Column(name="EMAIL",nullable=false)
	private String email;
	
	@Column(name="DATEOFBIRTH",nullable=false)
	private String dateOfBirth;
	
	@Column(name="ADHAR",nullable=false)
	private String adhar;
	
	@Column(name="PAN",nullable=false)
	private String pan;
	
	@Column(name="VOTERIDCARD",nullable=false)
	private String voterIdCard;
	
	@Column(name="ADDRESSLINE1",nullable=false)
	private String addressLine1;
	
	@Column(name="ADDRESSLINE2",nullable=false)
	private String addressLine2;
	
	@Column(name="TOWN",nullable=false)
	private String town;
	
	@Column(name="DISTRICT",nullable=false)
	private String district;
	
	@Column(name="STATE",nullable=false)
	private String state;
	
	@Column(name="COUNTRY",nullable=false)
	private String country;
	
	@Column(name="AREACODE",nullable=false)
	private String areaCode;
	
	@Column(name="PROFILEPICTURE",nullable=false)
	private String profilePicture;
	
	@Column(name="ADHARDOCUMENT",nullable=false)
	private String adharDocument;
	
	@Column(name="PANDOCUMENT",nullable=false)
	private String panDocument;
	
	@Column(name="SIGNATUREPICTURE",nullable=false)
	private String signaturePicture;
	
	@Column(name="VOTERIDCARDDOCUMENT",nullable=false)
	private String voterIdCardDocument;
	
	@Column(name="ROLE")
	private String role="USER";
	
	@Column(name="STATUS")
	private String status="PENDING";
	
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFather() {
		return father;
	}
	public void setFather(String father) {
		this.father = father;
	}
	public String getMother() {
		return mother;
	}
	public void setMother(String mother) {
		this.mother = mother;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
	public String getVoterIdCard() {
		return voterIdCard;
	}
	public void setVoterIdCard(String voterIdCard) {
		this.voterIdCard = voterIdCard;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	public String getAdharDocument() {
		return adharDocument;
	}
	public void setAdharDocument(String adharDocument) {
		this.adharDocument = adharDocument;
	}
	public String getPanDocument() {
		return panDocument;
	}
	public void setPanDocument(String panDocument) {
		this.panDocument = panDocument;
	}
	public String getSignaturePicture() {
		return signaturePicture;
	}
	public void setSignaturePicture(String signaturePicture) {
		this.signaturePicture = signaturePicture;
	}
	public String getVoterIdCardDocument() {
		return voterIdCardDocument;
	}
	public void setVoterIdCardDocument(String voterIdCardDocument) {
		this.voterIdCardDocument = voterIdCardDocument;
	}
	
	
}
