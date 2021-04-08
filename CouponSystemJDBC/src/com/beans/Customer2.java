package com.beans;

import java.util.ArrayList;

public class Customer2 {
	private int id;
	private String firsName;
	private String lastName;
	private String password;
	private String email;
	private ArrayList coupons;

	public Customer2(int id, String firsName, String lastName, String password, String email, ArrayList coupons) {
		super();
		this.id = id;
		this.firsName = firsName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.coupons = coupons;
	}

	public Customer2() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirsName() {
		return firsName;
	}

	public void setFirsName(String firsName) {
		this.firsName = firsName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList getCoupons() {
		return coupons;
	}

	public void setCoupons(ArrayList coupons) {
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "Customers [id=" + id + ", firsName=" + firsName + ", lastName=" + lastName + ", password=" + password
				+ ", email=" + email + ", coupons=" + coupons + "]";
	}

}
