package com.project.Services;

import java.time.LocalDate;
import java.util.List;

import com.project.Exceptions.TransactionException;
import com.project.Exceptions.WalletException;
import com.project.Model.Transaction;

public interface TransactionService {

	public Transaction addTransaction(Transaction tran);

	public List<Transaction> viewAllTransaction(Integer wallet_Id) throws WalletException;

	public List<Transaction> viewTransactionByDate(LocalDate from, LocalDate to, Integer wallet_Id)
			throws WalletException;

	public Transaction viewTransaction(Integer transactionId) throws TransactionException;

}
