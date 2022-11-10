package com.project.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Exceptions.BankAccountException;
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
	public Wallet addAccount(BankAccount account, Integer walletId) {
		
		BankAccount bb =  brepo.save(account);
		
		Optional<Wallet> w = wRepo.findById(walletId);
		
		return w.get();		
	}

	@Override
	public Wallet removeAccount(Integer accountNo, Integer walletId) throws BankAccountException {
		
		Optional<Wallet> w = wRepo.findById(walletId);
		
		List<BankAccount> acc = w.get().getBankAccounts();
		
		for (BankAccount a: acc) {
			if (a.getAccountNo() == accountNo) {
				brepo.deleteById(accountNo);
			}else {
				throw new BankAccountException();
			}
		}
		return w.get();
	}

	@Override
	public List<BankAccount> viewAccounts(Integer walletId) throws BankAccountException {
		
		Optional<Wallet> w = wRepo.findById(walletId);
		
		if(!w.isPresent()) {
			throw new BankAccountException();
		}
		
		return w.get().getBankAccounts();
	}
	
}
