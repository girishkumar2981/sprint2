package com.cg.ddims.login.dto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="login")
public class Login
{
	@Id
	@Column(name="userid")
	private long userId;
	@Column(name="username")
	private String userName;
	@Column(name="password")
	private String password;
	public Login() 
	{
	}
	public Login(long userId, String userName, String password) 
	{
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
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
}
