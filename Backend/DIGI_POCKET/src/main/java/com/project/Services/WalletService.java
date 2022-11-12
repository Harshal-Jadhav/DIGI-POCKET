package com.project.Services;

import com.project.Exceptions.CustomerException;
import com.project.Exceptions.InsufficientFundException;
import com.project.Exceptions.WalletException;
import com.project.Model.BankAccount;
import com.project.Model.Customer;
import com.project.Model.Wallet;

public interface WalletService {
	

	public Wallet showBalance(String mobileno) throws CustomerException;
	
	public String fundTransfer(String sourceMobileNo, String targetMobileNo, double amount)
			throws CustomerException, InsufficientFundException;
	
	public Customer depositAmount(String mobileNo, double amount, BankAccount acc)
			throws CustomerException, WalletException;
	
	public Customer updateAccount(Customer customer) throws CustomerException;
	
	public Wallet addMoney(Integer walletId, double amount, BankAccount acc) throws WalletException;
}
