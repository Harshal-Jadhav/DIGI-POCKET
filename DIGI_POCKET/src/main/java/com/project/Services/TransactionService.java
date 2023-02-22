package com.project.Services;

import java.time.LocalDate;
import java.util.List;

import com.project.Exceptions.InvalidCredentialsException;
import com.project.Exceptions.TransactionException;
import com.project.Exceptions.WalletException;
import com.project.Model.Transaction;

public interface TransactionService {

	public Transaction addTransaction(Transaction tran);

	public List<Transaction> viewAllTransaction(String key) throws WalletException, InvalidCredentialsException;

	public List<Transaction> viewTransactionByDate(LocalDate from, LocalDate to, String key)
			throws WalletException, InvalidCredentialsException;

	public Transaction viewTransaction(String key, Integer transactionId)
			throws TransactionException, InvalidCredentialsException;

}
