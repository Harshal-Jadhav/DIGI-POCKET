package com.project.Model;

import java.time.LocalDate;

import javax.persistence.Entity;

@Entity
public class Transaction {

	private Integer transactionId;
	private String transactionType;
	private LocalDate transactionDate;
	private double amount;
	private String description;

	// Entity RelationShips
	private Wallet wallet;

}
