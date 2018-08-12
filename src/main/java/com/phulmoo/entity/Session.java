package com.phulmoo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sessions")
@Entity
public class Session implements Serializable {
	private static final long serialVersionUID = -8605480737896301640L;
	@Id
	@GeneratedValue
	private long SessionID;
	private long UserID;
	private String UserName;
	private String Token = "xxx-xxx-xxx-xxx";
	private String Email;
	
	private String SessionCreateTime;
	private int isValid = 1;

	public long getSessionID() {
		return SessionID;
	}

	public void setSessionID(long sessionID) {
		SessionID = sessionID;
	}

	public long getUserID() {
		return UserID;
	}

	public void setUserID(long userID) {
		UserID = userID;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getSessionCreateTime() {
		return SessionCreateTime;
	}

	public void setSessionCreateTime(String sessionCreateTime) {
		SessionCreateTime = sessionCreateTime;
	}

	public int getIsValid() {
		return isValid;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

}
