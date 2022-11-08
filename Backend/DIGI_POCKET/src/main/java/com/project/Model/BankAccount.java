package com.project.Model;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class BankAccount {

	private Integer accountNo;
	private String ifscCode;
	private String bankName;
	private BigDecimal balance;

	// Entity Relationships
	private Wallet wallet;
}
