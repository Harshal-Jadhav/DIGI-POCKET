package com.project.Services;

import com.project.Exceptions.CustomerException;
import com.project.Exceptions.InsufficientFundException;
import com.project.Exceptions.WalletException;
import com.project.Model.Customer;
import com.project.Model.Wallet;

public interface WalletService {
	
	public Customer createAccount(String name, String mobileno, String password);

	public Wallet showBalance(String mobileno) throws CustomerException;
	
	public String fundTransfer(String sourceMobileNo, String targetMobileNo, double amount)
			throws CustomerException, InsufficientFundException;
	
	public Customer depositAmount(String mobileNo, double amount) throws CustomerException;
	
	public Customer updateAccount(Customer customer) throws CustomerException;
	
	public Customer addMoney(Wallet wallet, double amount) throws WalletException;
}
