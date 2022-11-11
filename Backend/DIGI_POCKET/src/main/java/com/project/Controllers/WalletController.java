package com.project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Exceptions.CustomerException;
import com.project.Exceptions.InsufficientFundException;
import com.project.Exceptions.WalletException;
import com.project.Model.Customer;
import com.project.Model.Wallet;
import com.project.Services.WalletService;

@RestController
@RequestMapping("/digipocket/wallet")
public class WalletController {

	@Autowired
	private WalletService wService;

	
	@GetMapping("/showbalance/{mob}")
	public ResponseEntity<Wallet> showWalletBalance(@PathVariable("mob") String mobile) throws CustomerException {
		Wallet wallet = wService.showBalance(mobile);

		return new ResponseEntity<Wallet>(wallet, HttpStatus.OK);
	}
	
	@PostMapping("/transfer/{s1}/{s2}/{amount}")
	public ResponseEntity<String> fundTransfer(@PathVariable("s1") String s1, @PathVariable("s2") String s2,
			@PathVariable("amount") Double amount) throws CustomerException, InsufficientFundException {
		String s = wService.fundTransfer(s1, s2, amount);

		return new ResponseEntity<String>(s, HttpStatus.OK);
	}
	
	// Need to connect with the bank
	@PutMapping("/deposit/{mob}/{amount}")
	public ResponseEntity<Customer> depositMoney(@PathVariable("mob") String mobile,
			@PathVariable("amount") double amount) throws CustomerException {
		Customer customer = wService.depositAmount(mobile, amount);
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
	}
	
//	NOT Wooking.
	@PutMapping("/update")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) throws CustomerException {
		Customer cus = wService.updateAccount(customer);
		return new ResponseEntity<Customer>(cus, HttpStatus.OK);
	}
	
//	need to connect with the bank
	@PostMapping("/deposit/{amount}")
	public ResponseEntity<Customer> AddMoney(@PathVariable("amount") double amount, @RequestBody Wallet wallet)
			throws CustomerException, WalletException {
		Customer customer = wService.addMoney(wallet, amount);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
