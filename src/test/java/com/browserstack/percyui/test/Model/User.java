package com.browserstack.percyui.test.Model;

public class User {

	private static User instance;
	private String userName;
	private String password;

	private User() {

	}

	public static User getInstance() {
		if (instance == null) {
			instance = new User();
		}

		return instance;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
