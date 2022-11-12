package com.project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Exceptions.CustomerException;
import com.project.Exceptions.LoginException;
import com.project.Model.Customer;
import com.project.Model.LoginDTO;
import com.project.Services.LoginService;
import com.project.Services.UserService;

@RestController
@RequestMapping("/digipocket")
public class AuthenticationController {

	@Autowired
	private LoginService lService;

	@Autowired
	private UserService uService;

	@PostMapping("/login")
	public ResponseEntity<String> loginIntoAccountHandler(@RequestBody LoginDTO login) throws LoginException {
		String str = lService.LoginToAccount(login);
		return new ResponseEntity<String>(str, HttpStatus.OK);
	}

	@GetMapping("/logout")
	public ResponseEntity<String> logoutFromAccountHandler(@PathVariable("key") String key) throws LoginException {
		String str = lService.LogOutFromAccount(key);
		return new ResponseEntity<String>(str, HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<Customer> registerAccountHandler(@RequestBody Customer customer) throws CustomerException {
		Customer cus = uService.RegisterCustomer(customer);
		return new ResponseEntity<Customer>(cus, HttpStatus.OK);
	}

}
