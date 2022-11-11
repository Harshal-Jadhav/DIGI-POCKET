package com.project.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CurrentUserSession {
	@Id
	private String mobile;
	private String key;
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	
	
	

}
