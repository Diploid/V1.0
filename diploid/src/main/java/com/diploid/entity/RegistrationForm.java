/**
 * @auther: Raghavendra 
 * @email: rgsingh.iit@gmail.com
 */
package com.diploid.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegistrationForm
{
 private Integer userId;
 private String userName;
 private String password;
 private String firstName;
 private String lastName;
 private String email;
 private String phone;
 private Date dob;
 private String gender;
 private List<String> interests = new ArrayList<String>();
 private boolean subscribeToNewsLetter;
public RegistrationForm() {
	super();
	// TODO Auto-generated constructor stub
}
public RegistrationForm(Integer userId, String userName, String password,
		String firstName, String lastName, String email, String phone,
		Date dob, String gender, List<String> interests,
		boolean subscribeToNewsLetter) {
	super();
	this.userId = userId;
	this.userName = userName;
	this.password = password;
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.phone = phone;
	this.dob = dob;
	this.gender = gender;
	this.interests = interests;
	this.subscribeToNewsLetter = subscribeToNewsLetter;
}
public Integer getUserId() {
	return userId;
}
public void setUserId(Integer userId) {
	this.userId = userId;
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
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public Date getDob() {
	return dob;
}
public void setDob(Date dob) {
	this.dob = dob;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public List<String> getInterests() {
	return interests;
}
public void setInterests(List<String> interests) {
	this.interests = interests;
}
public boolean isSubscribeToNewsLetter() {
	return subscribeToNewsLetter;
}
public void setSubscribeToNewsLetter(boolean subscribeToNewsLetter) {
	this.subscribeToNewsLetter = subscribeToNewsLetter;
}
 
 
 
}
