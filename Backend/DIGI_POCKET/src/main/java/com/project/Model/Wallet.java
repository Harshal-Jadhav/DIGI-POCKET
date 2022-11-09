package com.project.Model;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

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
	private Set<BankAccount> bankAccounts = new LinkedHashSet<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "wallet")
	private Set<Transaction> transactions = new LinkedHashSet<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "wallet")
	private Set<BillPayment> billPayments = new LinkedHashSet<>();

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "walletList")
	private Set<Beneficiary> beneficiaryList = new LinkedHashSet<>();

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

	public Set<BankAccount> getBankAccounts() {
		return bankAccounts;
	}

	public void setBankAccounts(Set<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Set<BillPayment> getBillPayments() {
		return billPayments;
	}

	public void setBillPayments(Set<BillPayment> billPayments) {
		this.billPayments = billPayments;
	}

	public Set<Beneficiary> getBeneficiaryList() {
		return beneficiaryList;
	}

	public void setBeneficiaryList(Set<Beneficiary> beneficiaryList) {
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
