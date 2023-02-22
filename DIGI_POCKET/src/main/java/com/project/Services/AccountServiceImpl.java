package com.project.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Exceptions.BankAccountException;
import com.project.Exceptions.InvalidCredentialsException;
import com.project.Exceptions.WalletException;
import com.project.Model.BankAccount;
import com.project.Model.CurrentUserSession;
import com.project.Model.Customer;
import com.project.Model.Wallet;
import com.project.Repositories.BankAccountRepo;
import com.project.Repositories.CustomerRepo;
import com.project.Repositories.SessionRepo;
import com.project.Repositories.WalletRepo;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private BankAccountRepo brepo;

	@Autowired
	private WalletRepo wRepo;

	@Autowired
	private SessionRepo session;

	@Autowired
	private CustomerRepo cRepo;

	@Override
	public Wallet addAccount(BankAccount account, String key) throws BankAccountException, InvalidCredentialsException {
		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new InvalidCredentialsException("Invalid Session key");

		Optional<Customer> customer = cRepo.findById(currSession.getMobile());
		Optional<Wallet> wallet = wRepo.findById(customer.get().getWallet().getWalletId());

		List<BankAccount> accList = wallet.get().getBankAccounts();

		boolean flag = false;

		for (BankAccount acc : accList) {
			if (acc.getAccountNo().equals(account.getAccountNo())) {
				flag = true;
				break;
			}
		}

		if (flag)
			throw new BankAccountException("Bank Has already been added.");

		account.setWallet(wallet.get());

		wallet.get().getBankAccounts().add(account);

		return wRepo.save(wallet.get());

	}

	@Override
	public Wallet removeAccount(Integer accountNo, String key)
			throws BankAccountException, WalletException, InvalidCredentialsException {

		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new InvalidCredentialsException("Invalid Session key");

		Optional<Customer> customer = cRepo.findById(currSession.getMobile());
		Optional<Wallet> wallet = wRepo.findById(customer.get().getWallet().getWalletId());

		if (!wallet.isPresent())
			throw new WalletException("Unknown User.");

		List<BankAccount> accList = wallet.get().getBankAccounts();

		boolean flag = false;

		for (BankAccount acc : accList) {
			if (acc.getAccountNo().equals(accountNo)) {
				flag = true;
				break;
			}
		}

		if (!flag)
			throw new BankAccountException("Provided Bank account no not linked to the wallet.");

		List<BankAccount> accounts = wallet.get().getBankAccounts();

		accounts.removeIf(ac -> ac.getAccountNo().equals(accountNo));
		Optional<BankAccount> a = brepo.findById(accountNo);
		a.get().setWallet(null);
		brepo.save(a.get());

		return wallet.get();
	}

	@Override
	public List<BankAccount> viewAccounts(String key) throws BankAccountException, InvalidCredentialsException {

		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new InvalidCredentialsException("Invalid Session key");

		Optional<Customer> customer = cRepo.findById(currSession.getMobile());
		Optional<Wallet> w = wRepo.findById(customer.get().getWallet().getWalletId());

		if (!w.isPresent()) {
			throw new BankAccountException("Invalid wallet Id.");
		}

		List<BankAccount> accList = w.get().getBankAccounts();

		if (accList.size() == 0)
			throw new BankAccountException("No Banks Found.");

		return accList;
	}

}
