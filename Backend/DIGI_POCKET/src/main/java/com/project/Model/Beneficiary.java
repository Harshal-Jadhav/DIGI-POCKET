package com.project.Model;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Beneficiary {

	private String name;
	@Id
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