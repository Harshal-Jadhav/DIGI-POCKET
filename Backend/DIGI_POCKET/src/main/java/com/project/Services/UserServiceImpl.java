package com.project.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Exceptions.CustomerException;
import com.project.Model.Customer;
import com.project.Repositories.CustomerRepo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private CustomerRepo cRepo;
	
	@Override
	public Customer registerCustomer(Customer customer) {
		
		Customer c1 = cRepo.save(customer);
		
		return customer;	
	}

	@Override
	public List<Customer> getAllCostomer() throws CustomerException {
		
		List<Customer> c1 = cRepo.findAll();
		
		if (c1.size()==0) {
			throw new CustomerException("Customers not found at all..!");
		}
		
		return c1;
	}

	@Override
	public Customer deleteCustomerByMobile(String mobile) throws CustomerException {
		
		Optional<Customer> c1 = cRepo.findById(mobile);
		
		if(!c1.isPresent()) {
			throw new CustomerException("No customer is present with this mobile number "+mobile);
		}
		
		return c1.get();
	}

}
