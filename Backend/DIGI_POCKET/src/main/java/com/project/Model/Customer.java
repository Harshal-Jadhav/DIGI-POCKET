package com.project.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {

	@Id
	private String mobile;
	private String name;
	private String password;

	// Entity Relationships
	private Wallet wallet;
	
	public Customer() {
		
	}

	public Customer(String mobile, String name, String password, Wallet wallet) {		
		this.mobile = mobile;
		this.name = name;
		this.password = password;
		this.wallet = wallet;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public void setPassword(String password) {
		this.password = password;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	
	
	
}
