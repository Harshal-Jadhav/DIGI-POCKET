package com.project.Model;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Beneficiary {

	@NotNull(message = "name should not be null")
	@NotEmpty(message = "name cannot be null")
	@Size(min = 3, max = 25, message = "name length should be between 3 to 25")
	private String name;

	@Id
	@NotNull(message = "mobile no. should not be null")
	@NotEmpty(message = "mobile no. should not be empty")
	@Size(min = 10, max = 12)
	private String mobile;

	// Entity Relationships
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "walletId")
	private Set<Wallet> walletList = new LinkedHashSet<>();

	public Beneficiary() {
		// TODO Auto-generated constructor stub
	}

	public Beneficiary(String name, String mobile, List<Wallet> walletList) {
		this.name = name;
		this.mobile = mobile;
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

	public Set<Wallet> getWalletList() {
		return walletList;
	}

	public void setWalletList(Set<Wallet> walletList) {
		this.walletList = walletList;
	}

}