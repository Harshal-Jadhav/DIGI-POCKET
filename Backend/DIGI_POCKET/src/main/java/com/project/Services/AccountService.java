package com.project.Services;

import java.util.List;

import com.project.Exceptions.BankAccountException;
import com.project.Exceptions.WalletException;
import com.project.Model.BankAccount;
import com.project.Model.Wallet;

public interface AccountService {
	
	public Wallet addAccount(BankAccount account, Integer walletId) throws BankAccountException;
	
	public Wallet removeAccount(Integer accountNo, Integer walletId) throws BankAccountException, WalletException;
	
	public List<BankAccount> viewAccounts(Integer walletId) throws BankAccountException;

}
