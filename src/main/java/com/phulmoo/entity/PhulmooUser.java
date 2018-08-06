package com.phulmoo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="users")
@Entity
public class PhulmooUser implements Serializable{
	private static final long serialVersionUID = -8605480737896301640L;
	@Id
	@GeneratedValue
	private long UserID;
	private String UserEmail;
	private String Username;
	private String UserPassword;
	private String UserFirstName;
	private String UserLastName;
	private String UserCity;
	private String UserState;
	private String UserZip;
	private String UserEmailVerified;
	private String UserRegistrationDate;
	private String UserVerificationCode;
	private String UserIP;
	private String UserPhone;
	private String UserFax;
	private String UserCountry;
	private String UserAddress;
	private String UserAddress2;
	public long getUserID() {
		return UserID;
	}
	public void setUserID(long userID) {
		UserID = userID;
	}
	public String getUserEmail() {
		return UserEmail;
	}
	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}
	
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getUserPassword() {
		return UserPassword;
	}
	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}
	public String getUserFirstName() {
		return UserFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		UserFirstName = userFirstName;
	}
	public String getUserLastName() {
		return UserLastName;
	}
	public void setUserLastName(String userLastName) {
		UserLastName = userLastName;
	}
	public String getUserCity() {
		return UserCity;
	}
	public void setUserCity(String userCity) {
		UserCity = userCity;
	}
	public String getUserState() {
		return UserState;
	}
	public void setUserState(String userState) {
		UserState = userState;
	}
	public String getUserZip() {
		return UserZip;
	}
	public void setUserZip(String userZip) {
		UserZip = userZip;
	}
	public String getUserEmailVerified() {
		return UserEmailVerified;
	}
	public void setUserEmailVerified(String userEmailVerified) {
		UserEmailVerified = userEmailVerified;
	}
	public String getUserRegistrationDate() {
		return UserRegistrationDate;
	}
	public void setUserRegistrationDate(String userRegistrationDate) {
		UserRegistrationDate = userRegistrationDate;
	}
	public String getUserVerificationCode() {
		return UserVerificationCode;
	}
	public void setUserVerificationCode(String userVerificationCode) {
		UserVerificationCode = userVerificationCode;
	}
	public String getUserIP() {
		return UserIP;
	}
	public void setUserIP(String userIP) {
		UserIP = userIP;
	}
	public String getUserPhone() {
		return UserPhone;
	}
	public void setUserPhone(String userPhone) {
		UserPhone = userPhone;
	}
	public String getUserFax() {
		return UserFax;
	}
	public void setUserFax(String userFax) {
		UserFax = userFax;
	}
	public String getUserCountry() {
		return UserCountry;
	}
	public void setUserCountry(String userCountry) {
		UserCountry = userCountry;
	}
	public String getUserAddress() {
		return UserAddress;
	}
	public void setUserAddress(String userAddress) {
		UserAddress = userAddress;
	}
	public String getUserAddress2() {
		return UserAddress2;
	}
	public void setUserAddress2(String userAddress2) {
		UserAddress2 = userAddress2;
	}
	@Override
	public String toString() {
		return "PhulmooUser [UserID=" + UserID + ", UserEmail=" + UserEmail + ", Username=" + Username
				+ ", UserPassword=" + UserPassword + ", UserFirstName=" + UserFirstName + ", UserLastName="
				+ UserLastName + ", UserCity=" + UserCity + ", UserState=" + UserState + ", UserZip=" + UserZip
				+ ", UserEmailVerified=" + UserEmailVerified + ", UserRegistrationDate=" + UserRegistrationDate
				+ ", UserVerificationCode=" + UserVerificationCode + ", UserIP=" + UserIP + ", UserPhone=" + UserPhone
				+ ", UserFax=" + UserFax + ", UserCountry=" + UserCountry + ", UserAddress=" + UserAddress
				+ ", UserAddress2=" + UserAddress2 + "]";
	}
	
}
