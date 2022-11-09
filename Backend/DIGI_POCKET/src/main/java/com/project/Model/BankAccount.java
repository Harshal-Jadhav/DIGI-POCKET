package com.project.Model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class BankAccount {

	@Id
	private Integer accountNo;
	
	@NotNull(message = "IFSC Code cannot be Null.")
	@NotEmpty(message = "IFSC Code cannot be Empty.")
	private String ifscCode;

	@NotNull(message = "Bank Name cannot be Null.")
	@NotEmpty(message = "Bank Name cannot be Empty.")
	private String bankName;
	
	@NotNull(message = "Balance cannot be null")
	@Min(0)
	private BigDecimal balance;

	// Entity Relationships
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "walletId")
	private Wallet wallet;

	public BankAccount() {
		// TODO Auto-generated constructor stub
	}

	public BankAccount(Integer accountNo, String ifscCode, String bankName, BigDecimal balance) {
		this.accountNo = accountNo;
		this.ifscCode = ifscCode;
		this.bankName = bankName;
		this.balance = balance;
	}

	public Integer getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Integer accountNo) {
		this.accountNo = accountNo;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

}
