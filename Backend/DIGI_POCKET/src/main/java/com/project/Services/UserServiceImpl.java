package com.project.Services;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.Exceptions.CustomerException;
import com.project.Model.Customer;
import com.project.Model.LoginDTO;
import com.project.Repositories.CustomerRepo;
import com.project.Repositories.SessionRepo;

public class UserServiceImpl implements UserService{
	@Autowired
	private CustomerRepo cRepo;
	@Autowired
	private SessionRepo sRepo;

	@Override
	public Customer RegisterCustomer(Customer customer) throws CustomerException {
		Customer registerdCustomer =  cRepo.findByMobile(customer.getMobile());
		
		if(registerdCustomer != null) {
			throw new CustomerException("Customer already exist with this mobile number");
		}else {
			return cRepo.save(customer);
		}
	}


	

}
