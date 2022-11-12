package com.project.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Exceptions.BankAccountException;
import com.project.Exceptions.WalletException;
import com.project.Model.BankAccount;
import com.project.Model.Wallet;
import com.project.Repositories.BankAccountRepo;
import com.project.Repositories.WalletRepo;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private BankAccountRepo brepo;
	
	@Autowired
	private WalletRepo wRepo;

	@Override
	public Wallet addAccount(BankAccount account, Integer walletId) throws BankAccountException {
		
		Optional<Wallet> wallet = wRepo.findById(walletId);

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
	public Wallet removeAccount(Integer accountNo, Integer walletId) throws BankAccountException, WalletException {
		
		Optional<Wallet> wallet = wRepo.findById(walletId);
		
		if (!wallet.isPresent())
			throw new WalletException("Unknown User.");

		List<BankAccount> accList =wallet.get().getBankAccounts();
		
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
	public List<BankAccount> viewAccounts(Integer walletId) throws BankAccountException {
		
		Optional<Wallet> w = wRepo.findById(walletId);
		
		if(!w.isPresent()) {
			throw new BankAccountException("Invalid wallet Id.");
		}
		
		return w.get().getBankAccounts();
	}
	
}
