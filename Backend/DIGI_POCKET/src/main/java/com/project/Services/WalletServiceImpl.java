package com.project.Services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.Exceptions.WalletException;
import com.project.Model.Customer;
import com.project.Model.Wallet;
import com.project.Repositories.WalletRepo;

public class WalletServiceImpl implements WalletService{
	
	@Autowired
	private WalletRepo wRepo;

	@Override
	public Customer createAccount(String name, String mobileno, String password) {
		
		Customer customer = new Customer();
		customer.setMobile(mobileno);
		customer.setName(name);
		customer.setPassword(password);
		
		wRepo.save(customer);
		return null;
	}

	@Override
	public Customer showBalance(String mobileno) throws WalletException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) throws WalletException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount) throws WalletException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getList() throws WalletException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateAccount(Customer customer) throws WalletException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer addMoney(Wallet wallet, double amount) throws WalletException {
		// TODO Auto-generated method stub
		return null;
	}

}
