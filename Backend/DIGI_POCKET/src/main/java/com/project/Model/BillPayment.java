package com.project.Model;

import java.time.LocalDate;

import javax.persistence.Entity;

@Entity
public class BillPayment {

	private Integer billId;
	private String billType;
	private double amount;
	private LocalDate paymentDate;
	
	// Entity Relationships;
	private Wallet wallet;
}
