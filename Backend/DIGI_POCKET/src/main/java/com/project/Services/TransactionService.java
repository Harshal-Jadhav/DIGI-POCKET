package com.project.Services;

import java.time.LocalDate;
import java.util.List;

import com.project.Exceptions.TransactionException;
import com.project.Model.Transaction;
import com.project.Model.Wallet;

public interface TransactionService {
	
	public Transaction addTransaction(Transaction tran);
	public List<Transaction> viewAllTransaction(Wallet wallet);
	public List<Transaction> viewTransactionByDate(LocalDate from, LocalDate to);
	public Transaction viewTransaction(Integer transactionId) throws TransactionException;

}
