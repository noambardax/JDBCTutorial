package com.beans;

public class Admin {
	private String email = "admin@admin.com";
	private String passwordString = "admin";

	public Admin() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordString() {
		return passwordString;
	}

	public void setPasswordString(String passwordString) {
		this.passwordString = passwordString;
	}

	@Override
	public String toString() {
		return "Admin [email=" + email + ", passwordString=" + passwordString + "]";
	}

}
