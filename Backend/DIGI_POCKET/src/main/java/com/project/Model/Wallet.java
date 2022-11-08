package com.project.Model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Wallet {

	@Id
	private Integer walletId;
	private BigDecimal balance;

	// Entity Relationships
	private Customer customer;

	private List<BankAccount> bankAccounts;
	
	private List<Transaction> transactions;

	private List<BillPayment> billPayments;
	
	private List<Beneficiary> beneficiaryList;
}
