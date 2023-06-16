package com.example.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
	private String name;
	private String password;
	private String email;
	private static int lastId=0;
	private int id;
	User(String name,String password,String email)
	{
		this.name=name;
		this.password=password;
		this.email=email;
		this.id=generateId();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	//Updates user's password
	public void resetPassword(String new_password) {
		this.password = new_password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	private int generateId() {
		return ++lastId;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", email=" + email + ", id=" + id + "]";
	}
	public boolean isUsersPassword(String password_input) {
		if(this.password.equals(password_input))
			return true;
		else
			return false;
	}
	//Checks if the email respects the following regex: name@provider.domain
	//The email and the provider can't start with a number
	public boolean isValidEmail(String email_input) {
		Pattern pattern=Pattern.compile("^[^0-9].+@.+\\\\.[^0-9].+$");
		Matcher matcher=pattern.matcher(email_input);
		if(matcher.find())
			return true;
		else
			return false;
	}
}
