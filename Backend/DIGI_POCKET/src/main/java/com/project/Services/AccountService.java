package com.project.Services;

import java.util.List;

import com.project.Exceptions.BankAccountException;
import com.project.Model.BankAccount;
import com.project.Model.Wallet;

public interface AccountService {
	
	public Wallet addAccount(BankAccount account);
	
	public Wallet removeAccount(Integer accountNo) throws BankAccountException;
	
	public BankAccount viewAccount(Wallet wallet);
	
	public List<BankAccount> viewAccounts(Wallet wallet);

}
