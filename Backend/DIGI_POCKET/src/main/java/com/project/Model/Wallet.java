package com.project.Model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
