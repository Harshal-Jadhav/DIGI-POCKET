package com.project.Services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Exceptions.CustomerException;
import com.project.Exceptions.InsufficientFundException;
import com.project.Exceptions.InvalidCredentialsException;
import com.project.Exceptions.WalletException;
import com.project.Model.BankAccount;
import com.project.Model.CurrentUserSession;
import com.project.Model.Customer;
import com.project.Model.Transaction;
import com.project.Model.Wallet;
import com.project.Repositories.BankAccountRepo;
import com.project.Repositories.CustomerRepo;
import com.project.Repositories.SessionRepo;
import com.project.Repositories.TransactionRepo;
import com.project.Repositories.WalletRepo;

@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
	private WalletRepo wRepo;

	@Autowired
	private CustomerRepo cRepo;

	@Autowired
	private BankAccountRepo bRepo;

	@Autowired
	private TransactionRepo tRepo;

	@Autowired
	private SessionRepo session;

	@Override
	public Wallet showBalance(String key) throws CustomerException, InvalidCredentialsException {

		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new InvalidCredentialsException("Invalid Session key.");
		Optional<Customer> customer = cRepo.findById(currSession.getMobile());
		Optional<Wallet> wallet = wRepo.findById(customer.get().getWallet().getWalletId());

		return wallet.get();

	}

	@Override
	public String fundTransfer(String targetMobileNo, double amount, String key)
			throws CustomerException, InsufficientFundException, InvalidCredentialsException {

		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new InvalidCredentialsException("Invalid Session key.");
		Optional<Customer> customer1 = cRepo.findById(currSession.getMobile());

		if (!customer1.isPresent()) {
			throw new CustomerException("Sender account is not found");
		}

		Optional<Customer> customer2 = cRepo.findById(targetMobileNo);

		if (!customer2.isPresent()) {
			throw new CustomerException("Receiver account is not found");
		}

		if (customer1.get().getWallet().getBalance() < amount)
			throw new InsufficientFundException("Insufficient Funds in your Wallet.");

		Transaction tran = new Transaction();
		tran.setAmount(amount);
		tran.setDescription("Funds Transfered to " + customer2.get().getName());
		tran.setTransactionDate(LocalDate.now());
		tran.setTransactionType("Fund Transfer");
		tran.setWallet(customer1.get().getWallet());

		tRepo.save(tran);

		Transaction tran2 = new Transaction();
		tran2.setAmount(amount);
		tran2.setDescription("Funds Received from " + customer1.get().getName());
		tran2.setTransactionDate(LocalDate.now());
		tran2.setTransactionType("Funds Transfer");
		tran2.setWallet(customer2.get().getWallet());

		tRepo.save(tran2);

		double updatedBalance1 = customer1.get().getWallet().getBalance() - amount;
		customer1.get().getWallet().setBalance(updatedBalance1);
		cRepo.save(customer1.get());

		double updatedBalance2 = customer2.get().getWallet().getBalance() + amount;
		customer2.get().getWallet().setBalance(updatedBalance2);
		cRepo.save(customer2.get());

		return "Fund is transfered successfully...";
	}

	@Override
	public Customer updateAccount(Customer customer, String key) throws CustomerException, InvalidCredentialsException {

		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new InvalidCredentialsException("Invalid Session key.");
		Optional<Customer> customer1 = cRepo.findById(currSession.getMobile());

		if (!customer1.isPresent())
			throw new CustomerException("Customer Not Found.");

		if (customer.getName() != null)
			customer1.get().setName(customer.getName());
		if (customer.getPassword() != null)
			customer1.get().setPassword(customer.getPassword());

		return cRepo.save(customer1.get());

	}

	@Override
	public Wallet addMoney(String key, double amount, BankAccount acc)
			throws WalletException, InvalidCredentialsException {

		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new InvalidCredentialsException("Invalid Session key.");
		Optional<Customer> customer = cRepo.findById(currSession.getMobile());
		Optional<Wallet> wallet1 = wRepo.findById(customer.get().getWallet().getWalletId());

		if (wallet1.isPresent()) {

			Optional<BankAccount> account = bRepo.findById(acc.getAccountNo());
			if (!account.isPresent() || account.get().getWallet().getWalletId() != wallet1.get().getWalletId())
				throw new WalletException("bankAccount Not Found.");

			Transaction tran = new Transaction();
			tran.setAmount(amount);
			tran.setDescription("Funds Added From Bank Account " + acc.getAccountNo());
			tran.setTransactionDate(LocalDate.now());
			tran.setTransactionType("Self Deposit.");
			tran.setWallet(wallet1.get());

			tRepo.save(tran);

			account.get().setBalance(account.get().getBalance() - amount);
			wallet1.get().setBalance(wallet1.get().getBalance() + amount);
//			wallet1.get().getTransactions().add(tran);

			bRepo.save(account.get());
			Wallet w = wRepo.save(wallet1.get());
			return w;
		} else {
			throw new WalletException("Wallet not found");
		}
	}

}
