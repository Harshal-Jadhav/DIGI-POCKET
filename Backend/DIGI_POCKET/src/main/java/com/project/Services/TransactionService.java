package com.project.Services;

import java.util.Date;
import java.util.List;

import com.project.Exceptions.TransactionException;
import com.project.Exceptions.WalletException;
import com.project.Model.Transaction;
import com.project.Model.Wallet;

public interface TransactionService {

	public Transaction addTransaction(Transaction tran);

	public List<Transaction> viewAllTransaction(Wallet wallet) throws WalletException;

	public List<Transaction> viewTransactionByDate(Date from, Date to, Integer wallet_Id) throws WalletException;

	public Transaction viewTransaction(Integer transactionId) throws TransactionException;

}
