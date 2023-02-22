package com.project.Services;

import java.util.List;

import com.project.Exceptions.BankAccountException;
import com.project.Exceptions.InvalidCredentialsException;
import com.project.Exceptions.WalletException;
import com.project.Model.BankAccount;
import com.project.Model.Wallet;

public interface AccountService {

	public Wallet addAccount(BankAccount account, String key) throws BankAccountException, InvalidCredentialsException;

	public Wallet removeAccount(Integer accountNo, String key)
			throws BankAccountException, WalletException, InvalidCredentialsException;

	public List<BankAccount> viewAccounts(String key) throws BankAccountException, InvalidCredentialsException;

}
