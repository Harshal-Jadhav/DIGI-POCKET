package com.project.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Exceptions.CustomerException;
import com.project.Model.Customer;
import com.project.Model.Wallet;
import com.project.Repositories.CustomerRepo;
import com.project.Repositories.SessionRepo;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private CustomerRepo cRepo;
	@Autowired
	private SessionRepo sRepo;

	@Override
	public Customer RegisterCustomer(Customer customer) throws CustomerException {
		Customer registerdCustomer = cRepo.findByMobile(customer.getMobile());

		if (registerdCustomer != null) {
			throw new CustomerException("Customer already exist with this mobile number");
		} else {
			Wallet wallet = new Wallet(0);
			wallet.setCustomer(customer);
			customer.setWallet(wallet);
			return cRepo.save(customer);
		}
	}

}
