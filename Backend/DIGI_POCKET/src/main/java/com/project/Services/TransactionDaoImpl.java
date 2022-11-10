package com.project.Services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.Exceptions.TransactionException;
import com.project.Model.Transaction;
import com.project.Model.Wallet;
import com.project.Repositories.TransactionRepo;

public class TransactionDaoImpl implements TransactionService{
	
	@Autowired
	private TransactionRepo tRepo;

	@Override
	public Transaction addTransaction(Transaction tran) {
		Transaction transaction = tRepo.save(tran);
		return transaction;
	}

	@Override
	public List<Transaction> viewAllTransaction(Wallet wallet) {
		return wallet.getTransactions();
	}

	@Override
	public List<Transaction> viewTransactionByDate(LocalDate from, LocalDate to) {
		List<Transaction> transactions = tRepo.getTransactionsByDate(from, to);
		return transactions;
	}

	@Override
	public Transaction viewTransaction(Integer transactionId) throws TransactionException {
		return tRepo.findById(transactionId).orElseThrow(() -> new TransactionException("wrong transaction id"));
	}

}
