package com.example.demo;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.persistence.*;

@Table(name = "user_table")
@Entity
public class User {
	@Column(name = "name", unique = true)
	private String name;
	@Column(name = "password", unique = true)
	private String password;
	@Column(name = "email", unique = true)
	private String email;

	public enum Gender {
		MALE, FEMALE
	}

	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private double balance;
	@Temporal(TemporalType.DATE)
	private Date birthDate;

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	User(String name, String password, String email) {
		this.name = name;
		this.password = password;
		this.email = email;
	}

	public double getBalance() {
		return balance;
	}

	public void addBalance(double amount) {
		this.balance += amount;
	}

	public void removeBalance(double amount) {
		this.balance -= amount;
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

	// Updates user's password
	public void resetPassword(String new_password) {
		this.password = new_password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", email=" + email + ", id=" + id + "]";
	}

	public boolean isUsersPassword(String password_input) {
		if (this.password.equals(password_input))
			return true;
		else
			return false;
	}

	// Checks if the email respects the following regex: name@provider.domain
	// The email and the provider can't start with a number
	public boolean isValidEmail(String email_input) {
		Pattern pattern = Pattern.compile("^[^0-9].+@.+\\\\.[^0-9].+$");
		Matcher matcher = pattern.matcher(email_input);
		if (matcher.find())
			return true;
		else
			return false;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public User() {}
}
