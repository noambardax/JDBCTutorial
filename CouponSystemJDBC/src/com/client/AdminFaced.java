package com.client;

import com.beans.Admin;

public class AdminFaced extends ClientFaced {

	public AdminFaced() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static boolean logIn(String email, String password) {
		Admin admin = new Admin();
		if (admin.getEmail().equals("admin@admin.com") 
				&& admin.getPasswordString().equals("admin")) {
			return true;

		}
		return false;

	}
}
