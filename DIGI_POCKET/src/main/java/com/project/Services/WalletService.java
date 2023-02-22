package com.project.Services;

import com.project.Exceptions.CustomerException;
import com.project.Exceptions.InsufficientFundException;
import com.project.Exceptions.InvalidCredentialsException;
import com.project.Exceptions.WalletException;
import com.project.Model.BankAccount;
import com.project.Model.Customer;
import com.project.Model.Wallet;

public interface WalletService {

	public Wallet showBalance(String key) throws CustomerException, InvalidCredentialsException;

	public String fundTransfer(String targetMobileNo, double amount, String key)
			throws CustomerException, InsufficientFundException, InvalidCredentialsException;

	public Customer updateAccount(Customer customer, String key) throws CustomerException, InvalidCredentialsException;

	public Wallet addMoney(String key, double amount, BankAccount acc)
			throws WalletException, InvalidCredentialsException;
}
