package com.project.Model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer walletId;

	@NotNull(message = "balance cannot be Null.")
	@Min(0)
	private BigDecimal balance;

	// Entity Relationships
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "wallet")
	private Customer customer;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "wallet")
	private List<BankAccount> bankAccounts = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "wallet")
	private List<Transaction> transactions = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "wallet")
	private List<BillPayment> billPayments = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "walletList")
	private List<Beneficiary> beneficiaryList = new ArrayList<>();

	public Wallet() {
		// TODO Auto-generated constructor stub
	}

	public Wallet(BigDecimal balance) {
		this.balance = balance;
	}

	public Integer getWalletId() {
		return walletId;
	}

	public void setWalletId(Integer walletId) {
		this.walletId = walletId;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<BankAccount> getBankAccounts() {
		return bankAccounts;
	}

	public void setBankAccounts(List<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public List<BillPayment> getBillPayments() {
		return billPayments;
	}

	public void setBillPayments(List<BillPayment> billPayments) {
		this.billPayments = billPayments;
	}

	public List<Beneficiary> getBeneficiaryList() {
		return beneficiaryList;
	}

	public void setBeneficiaryList(List<Beneficiary> beneficiaryList) {
		this.beneficiaryList = beneficiaryList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(walletId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wallet other = (Wallet) obj;
		return Objects.equals(walletId, other.walletId);
	}

}
