package com.project.Services;

import com.project.Exceptions.CustomerException;
import com.project.Model.Customer;

public interface UserService {
	
	public Customer RegisterCustomer(Customer customer) throws CustomerException;

}
