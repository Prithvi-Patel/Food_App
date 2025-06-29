package com.Food_App.Models;

import java.sql.Timestamp;

public class User {
	
	int userId;
	String name;
	String user_name;
	String password;
	String email;
	String phonenumber;
	String address;
	String role;
	Timestamp created_Date;
	Timestamp last_Login_Date;
	
	public User() {
		
	}
	
	public User(int userId, String name, String user_name, String password, String email, String phonenumber,
			String address, String role, Timestamp created_Date, Timestamp last_Login_Date) {
		super();
		this.userId = userId;
		this.name = name;
		this.user_name = user_name;
		this.password = password;
		this.email = email;
		this.phonenumber = phonenumber;
		this.address = address;
		this.role = role;
		this.created_Date = created_Date;
		this.last_Login_Date = last_Login_Date;
	}
	
	public User(String name, String user_name, String password, String email, String phonenumber, String address,
				String role) {
		super();
		this.name = name;
		this.user_name = user_name;
		this.password = password;
		this.email = email;
		this.phonenumber = phonenumber;
		this.address = address;
		this.role = role;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUsername(String user_name) {
		this.user_name = user_name;
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

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Timestamp getCreated_Date() {
		return created_Date;
	}

	public void setCreated_Date(Timestamp created_Date) {
		this.created_Date = created_Date;
	}

	public Timestamp getLast_Login_Date() {
		return last_Login_Date;
	}

	public void setLast_Login_Date(Timestamp last_Login_Date) {
		this.last_Login_Date = last_Login_Date;
	}
	
	@Override
	public String toString() {
		return "User: " + name +" "+ user_name + " " + password+ " " + email +" "+address ;
	}
}
