package com.project.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customer {

	@Id
	@NotNull(message = "Mobile no cannot be Null.")
	@NotEmpty(message = "Mobile no cannot be Empty.")
	@Max(10)
	private String mobile;

	@NotNull(message = "Customer name cannot be Null.")
	@NotEmpty(message = "Customer name cannot be Empty.")
	private String name;
	
	@NotNull(message = "passwod cannot be Null.")
	@NotEmpty(message = "password cannot be Empty.")
	private String password;

//	// Entity Relationships
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "walletId")
	@JsonIgnore
	private Wallet wallet;
	
	public Customer() {
		
	}

	public Customer(String mobile, String name, String password) {
		this.mobile = mobile;
		this.name = name;
		this.password = password;
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
