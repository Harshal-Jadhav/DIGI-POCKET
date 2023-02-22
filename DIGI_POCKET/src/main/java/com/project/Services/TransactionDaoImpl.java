package com.project.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Exceptions.InvalidCredentialsException;
import com.project.Exceptions.TransactionException;
import com.project.Exceptions.WalletException;
import com.project.Model.CurrentUserSession;
import com.project.Model.Customer;
import com.project.Model.Transaction;
import com.project.Model.Wallet;
import com.project.Repositories.CustomerRepo;
import com.project.Repositories.SessionRepo;
import com.project.Repositories.TransactionRepo;
import com.project.Repositories.WalletRepo;

@Service
public class TransactionDaoImpl implements TransactionService {

	@Autowired
	private TransactionRepo tRepo;

	@Autowired
	private WalletRepo wRepo;

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private SessionRepo session;

	@Override
	public Transaction addTransaction(Transaction tran) {
		Transaction transaction = tRepo.save(tran);
		return transaction;
	}

	@Override
	public List<Transaction> viewAllTransaction(String key) throws WalletException, InvalidCredentialsException {

		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new InvalidCredentialsException("Invalid Session key.");
		Optional<Customer> customer = customerRepo.findById(currSession.getMobile());
		Optional<Wallet> wal = wRepo.findById(customer.get().getWallet().getWalletId());

		if (!wal.isPresent())
			throw new WalletException("Invalid Wallet Details.");

		return wal.get().getTransactions();
	}

	@Override
	public List<Transaction> viewTransactionByDate(LocalDate from, LocalDate to, String key)
			throws WalletException, InvalidCredentialsException {
		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new InvalidCredentialsException("Invalid Session key.");
		Optional<Customer> customer = customerRepo.findById(currSession.getMobile());
		Optional<Wallet> wallet = wRepo.findById(customer.get().getWallet().getWalletId());

		if (!wallet.isPresent())
			throw new WalletException("Invalid User.");

		List<Transaction> tranList = wallet.get().getTransactions();
		List<Transaction> filtered = new ArrayList<>();
		for (Transaction t : tranList) {
			if ((t.getTransactionDate().isAfter(from) && t.getTransactionDate().isBefore(to))
					|| t.getTransactionDate().equals(from) || t.getTransactionDate().equals(to)) {
				filtered.add(t);
			}
		}

		if (filtered.size() == 0)
			throw new WalletException("No Transactions Found.");

		return filtered;
	}

	@Override
	public Transaction viewTransaction(String key, Integer transactionId)
			throws TransactionException, InvalidCredentialsException {

		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new InvalidCredentialsException("Invalid Session key.");
		Optional<Customer> customer = customerRepo.findById(currSession.getMobile());
		Optional<Wallet> wallet = wRepo.findById(customer.get().getWallet().getWalletId());
		Optional<Transaction> tran = tRepo.findById(transactionId);

		if (!tran.isPresent())
			throw new TransactionException("Invalid transaction id");
		if (tran.get().getWallet().getWalletId() != wallet.get().getWalletId())
			throw new TransactionException("Transaction not Found");

		return tran.get();
	}

}
