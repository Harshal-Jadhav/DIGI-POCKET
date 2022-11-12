package com.project.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customer {

	@NotNull(message = "name should not be null")
	@NotEmpty(message = "name cannot be null")
	@Size(min = 3, max = 25, message = "name length should be between 3 to 25")
	private String name;

	@Id
	@NotNull(message = "mobile no. should not be null")
	@NotEmpty(message = "mobile no. should not be empty")
	@Size(max = 10)
	private String mobile;
	
	@NotNull(message = "password no. should not be null")
	@NotEmpty(message = "password no. should not be empty")
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Wallet wallet;

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(
			@NotNull(message = "name should not be null") @NotEmpty(message = "name cannot be null") @Size(min = 3, max = 25, message = "name length should be between 3 to 25") String name,
			@NotNull(message = "mobile no. should not be null") @NotEmpty(message = "mobile no. should not be empty") @Size(max = 10) String mobile,
			@NotNull(message = "password no. should not be null") @NotEmpty(message = "password no. should not be empty") String password) {
		this.name = name;
		this.mobile = mobile;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	

}