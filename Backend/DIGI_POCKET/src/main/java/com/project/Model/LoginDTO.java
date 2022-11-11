package com.project.Model;

public class LoginDTO {
	
	private String mobile;
	private String password;
	
	
	
	public LoginDTO(String mobile, String password) {
		super();
		this.mobile = mobile;
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginDTO [mobile=" + mobile + ", password=" + password + "]";
	}
	
	

}
