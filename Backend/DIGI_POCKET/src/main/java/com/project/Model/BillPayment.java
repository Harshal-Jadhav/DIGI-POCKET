package com.project.Model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BillPayment {

	private Integer billId;
	private String billType;
	private double amount;
	private LocalDate paymentDate;
	
	// Entity Relationships;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "walletId")
	private Wallet wallet;
}
