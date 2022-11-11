package com.project.Model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer transactionId;

	@NotNull(message = "Transaction Type cannot be Null.")
	@NotEmpty(message = "Transaction Type cannot be Empty.")
	private String transactionType;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@NotNull
	private Date transactionDate;

	@NotNull(message = "amount cannot be Null.")
	@NotEmpty(message = "amount cannot be Empty.")
	@Min(1)
	private double amount;

	@NotNull(message = "Description cannot be Null.")
	@NotEmpty(message = "Description cannot be Empty.")
	private String description;

	// Entity RelationShips
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "walletId")
	@JsonIgnore
	private Wallet wallet;

	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	public Transaction(Integer transactionId, String transactionType, Date transactionDate, double amount,
			String description) {
		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
		this.amount = amount;
		this.description = description;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

}
