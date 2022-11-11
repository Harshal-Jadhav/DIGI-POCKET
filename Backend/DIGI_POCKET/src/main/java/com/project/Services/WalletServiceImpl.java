package com.project.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Exceptions.CustomerException;
import com.project.Exceptions.InsufficientFundException;
import com.project.Exceptions.WalletException;
import com.project.Model.Customer;
import com.project.Model.Wallet;
import com.project.Repositories.CustomerRepo;
import com.project.Repositories.WalletRepo;

@Service
public class WalletServiceImpl implements WalletService{
	
	@Autowired
	private WalletRepo wRepo;
	
	@Autowired
	private CustomerRepo cRepo;

	@Override
	public Customer createAccount(String name, String mobileno, String password) {

		Wallet wa = new Wallet();
		wa.setBalance(0);

		Customer customer = new Customer();
		customer.setMobile(mobileno);
		customer.setName(name);
		customer.setPassword(password);
		customer.setWallet(wa);

		Customer customer1 = cRepo.save(customer);

		return customer1;
	}

	@Override
	public Wallet showBalance(String mobileno) throws CustomerException {
		
		Optional<Customer> cst = cRepo.findById(mobileno);
		
		if(cst.isPresent()) {
			return cst.get().getWallet();
			
		} else 
			throw new CustomerException("Customer not found");
	
		
	}

	@Override
	public String fundTransfer(String sourceMobileNo, String targetMobileNo, double amount)
			throws CustomerException, InsufficientFundException {
		
		Optional<Customer> customer1 = cRepo.findById(sourceMobileNo);
		
		if(!customer1.isPresent()) {
			throw new CustomerException("Sender account is not found");
		}
		
		Optional<Customer> customer2 = cRepo.findById(targetMobileNo);
		
		if(!customer2.isPresent()) {
			throw new CustomerException("Receiver account is not found");
		}
		
		
		if (customer1.get().getWallet().getBalance() < amount)
			throw new InsufficientFundException("Insufficient Funds in your Wallet.");
		
		double updatedBalance1 = customer1.get().getWallet().getBalance()-amount;
		customer1.get().getWallet().setBalance(updatedBalance1);
		cRepo.save(customer1.get());
		
		double updatedBalance2 = customer2.get().getWallet().getBalance()+amount;
		customer2.get().getWallet().setBalance(updatedBalance2);
		cRepo.save(customer2.get());
		
		
		return "Fund is transfered successfully...";
	}

	@Override
	public Customer depositAmount(String mobileNo, double amount) throws CustomerException {
		
		Optional<Customer> customer = cRepo.findById(mobileNo);
		
		if(customer.isEmpty()) {
			throw new CustomerException("Customer not found");
		}
		
		double updatedBalance = customer.get().getWallet().getBalance()+amount;
		customer.get().getWallet().setBalance(updatedBalance);
		
		Customer customer1 = cRepo.save(customer.get());
		
		return customer1;
	}


	@Override
	public Customer updateAccount(Customer customer) throws CustomerException {
		
		String mobileno = customer.getMobile();
		
		Optional<Customer> customer1 = cRepo.findById(mobileno);
		
		if(customer1.isPresent()) {
			
			return cRepo.save(customer);
			
		} else {
			throw new CustomerException("Customer not found");
		}
		
	}

	@Override
	public Customer addMoney(Wallet wallet, double amount) throws WalletException {
		
		Integer id = wallet.getWalletId();
		
		Optional<Wallet> wallet1 = wRepo.findById(id);
		
		if(wallet1.isPresent()) {
			wallet1.get().setBalance(wallet1.get().getBalance()+amount);
			wallet = wRepo.save(wallet1.get());
			return wallet.getCustomer();
		} else {
			throw new WalletException("Wallet not found");
		}
	}

}
