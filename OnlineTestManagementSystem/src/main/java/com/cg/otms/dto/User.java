package com.cg.otms.dto;

import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity                               
@Table(name="Userdetails")            
public class User {
	//variable definition
	@Id                               
	@Column(length=10)               
private String userId;
	//Implementing onetoOne relation and cascade=CascadeType.ALL is used to prevent flushing
	@OneToOne(mappedBy="user",cascade=CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval=true) 
	@JoinColumn(name = "userTest")   
	private Test userTest;
	@Column(length=15)                  //specifying the column length
private String password;
	@Column(length=15)
private String rePassword;
	@Column(length=15)                   // specifying the column length
private BigInteger phonenumber;
	@Column(length=25)
	private String emailId;
	//Default constructor
	public User()
	{
		
	}
	//parameterized constructor
public User(String userId, Test userTest, String password, String rePassword, BigInteger phonenumber,
			String emailId) {
		super();
		this.userId = userId;
		this.userTest = userTest;
		this.password = password;
		this.rePassword = rePassword;
		this.phonenumber = phonenumber;
		this.emailId = emailId;
	}
/**
 *Public getter and setter for the private variables declared
 */
public String getUserId() {
	return userId;
}
public Test getUserTest() {
	return userTest;
}
public void setUserTest(Test userTest) {
	this.userTest = userTest;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getRePassword() {
	return rePassword;
}
public void setRePassword(String rePassword) {
	this.rePassword = rePassword;
}
public BigInteger getPhonenumber() {
	return phonenumber;
}
public void setPhonenumber(BigInteger phonenumber) {
	this.phonenumber = phonenumber;
}
public String getEmailId() {
	return emailId;
}
public void setEmailId(String emailId) {
	this.emailId = emailId;
}

}