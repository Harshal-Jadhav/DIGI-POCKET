package com.project.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Exceptions.TransactionException;
import com.project.Exceptions.WalletException;
import com.project.Model.Transaction;
import com.project.Model.Wallet;
import com.project.Repositories.TransactionRepo;
import com.project.Repositories.WalletRepo;

@Service
public class TransactionDaoImpl implements TransactionService{
	
	@Autowired
	private TransactionRepo tRepo;

	@Autowired
	private WalletRepo wRepo;

	@Override
	public Transaction addTransaction(Transaction tran) {
		Transaction transaction = tRepo.save(tran);
		return transaction;
	}

	@Override
	public List<Transaction> viewAllTransaction(Integer wallet_Id) throws WalletException {
		Optional<Wallet> wal = wRepo.findById(wallet_Id);

		if (!wal.isPresent())
			throw new WalletException("Invalid Wallet Details.");

		return wal.get().getTransactions();
	}

	@Override
	public List<Transaction> viewTransactionByDate(Date from, Date to, Integer wallet_Id)
			throws WalletException {
		Optional<Wallet> wallet = wRepo.findById(wallet_Id);
		
		if (!wallet.isPresent())
			throw new WalletException("Invalid User.");
		
		List<Transaction> tranList =  wallet.get().getTransactions();
		List<Transaction> filtered = new ArrayList<>();
		for(Transaction t : tranList) {
			if(t.getTransactionDate().compareTo(from)>=0 && t.getTransactionDate().compareTo(to)<=0) {
				filtered.add(t);
			}
		}

		return filtered;
	}

	@Override
	public Transaction viewTransaction(Integer transactionId) throws TransactionException {
		return tRepo.findById(transactionId).orElseThrow(() -> new TransactionException("Invalid transaction id"));
	}

}
