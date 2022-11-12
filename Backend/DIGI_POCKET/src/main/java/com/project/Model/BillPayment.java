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
public class BillPayment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer billId;

	@NotNull(message = "Bill Type cannot be Null.")
	@NotEmpty(message = "Bill Type cannot be Empty.")
	private String billType;

	@NotNull(message = "Amount cannot be null")
	@Min(1)
	private double amount;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@NotNull
	private Date paymentDate;

	// Entity Relationships;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "walletId")
	@JsonIgnore
	private Wallet wallet;

	public BillPayment() {
		// TODO Auto-generated constructor stub
	}

	public BillPayment(Integer billId, String billType, double amount, Date paymentDate) {
		this.billId = billId;
		this.billType = billType;
		this.amount = amount;
		this.paymentDate = paymentDate;
	}

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

}
