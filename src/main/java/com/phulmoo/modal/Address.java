package com.phulmoo.modal;

import java.io.Serializable;

public class Address implements Serializable {

	private static final long serialVersionUID = -8082144561215933565L;
	private String name;
	private String emailAddress;
	private String homeAddress;
	private String city;
	private String postalCode;
	private String phoneNumber;
	private String orderId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "Address [name=" + name + ", emailAddress=" + emailAddress + ", homeAddress=" + homeAddress + ", city="
				+ city + ", postalCode=" + postalCode + ", phoneNumber=" + phoneNumber + "]";
	}
}