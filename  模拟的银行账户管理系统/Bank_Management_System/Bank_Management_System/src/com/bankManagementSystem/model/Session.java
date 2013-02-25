package com.bankManagementSystem.model;

public class Session {
	public static String name;
	public static String password;
	public static int id;

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		Session.name = name;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		Session.password = password;
	}

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		Session.id = id;
	}

}
