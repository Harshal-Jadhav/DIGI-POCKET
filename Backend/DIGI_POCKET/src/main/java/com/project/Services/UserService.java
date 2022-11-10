package com.project.Services;

import java.util.List;

import com.project.Exceptions.CustomerException;
import com.project.Model.Customer;

public interface UserService {
	
	public Customer registerCustomer(Customer customer);
	
	public List<Customer> getAllCostomer() throws CustomerException;
	
	public Customer deleteCustomerByMobile(String mobile) throws CustomerException;
	
}
