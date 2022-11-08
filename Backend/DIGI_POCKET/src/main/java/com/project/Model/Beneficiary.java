package com.project.Model;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class Beneficiary {

	private String name;
	private String mobile;

	// Entity Relationships
	private List<Wallet> walletList;

}