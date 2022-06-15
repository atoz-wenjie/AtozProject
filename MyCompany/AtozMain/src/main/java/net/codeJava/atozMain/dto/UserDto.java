package net.codeJava.atozMain.dto;

import net.codeJava.atozEntity.User;

public class UserDto {
	
	private String userName;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String loginTime;
	
	public UserDto() {
		
	}
	
	public UserDto(User user) {
		this.userName = user.getUserName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.loginTime = user.getLoginTime();
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	
	public String toString() {
		return "userName:"+userName + "\n" + "firstName:"+firstName + "\n" + "lastName:"+ lastName +"\n" + "email:"+ email +"\n" + "password:"+ password +"\n";
		
	}
}
