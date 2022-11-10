package com.project.Services;

import java.math.BigDecimal;
import java.util.List;

import com.project.Exceptions.WalletException;
import com.project.Model.Customer;
import com.project.Model.Wallet;

public interface WalletService {
	
	public Customer createAccount(String name, String mobileno, String password);

	public Customer showBalance(String mobileno) throws WalletException;
	
	public String fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) throws WalletException;
	
	public Customer depositAmount(String mobileNo, BigDecimal amount) throws WalletException;
	
	public List<Customer> getList() throws WalletException;
	
	public Customer updateAccount(Customer customer) throws WalletException;
	
	public Customer addMoney(Wallet wallet, double amount) throws WalletException;
}
