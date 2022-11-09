package com.project.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Beneficiary {

	@NotNull(message = "name should not be null")
	@Size(min = 3,max = 25,message = "name length is not between 3 to 25")
	private String name;
	
	@Id
	@NotNull(message = "mobile no. should not be null")
	@Size(min = 10,max = 12)
	private String mobile;

	// Entity Relationships
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Wallet> walletList;

	
	public Beneficiary(String name, String mobile, List<Wallet> walletList) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.walletList = walletList;
	}
	
	public Beneficiary() {
		super();
		// TODO Auto-generated constructor stub
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

	public List<Wallet> getWalletList() {
		return walletList;
	}

	public void setWalletList(List<Wallet> walletList) {
		this.walletList = walletList;
	}

	
	@Override
	public String toString() {
		return "Beneficiary [name=" + name + ", mobile=" + mobile + "]";
	}
	

}