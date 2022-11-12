package com.project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.Exceptions.CustomerException;
import com.project.Exceptions.InsufficientFundException;
import com.project.Exceptions.InvalidCredentialsException;
import com.project.Exceptions.WalletException;
import com.project.Model.BankAccount;
import com.project.Model.Customer;
import com.project.Model.Wallet;
import com.project.Services.WalletService;

@RestController
@RequestMapping("/digipocket/wallet")
public class WalletController {

	@Autowired
	private WalletService wService;

	@GetMapping("/view")
	public ResponseEntity<Wallet> showWalletBalanceHandler(@RequestParam String key)
			throws CustomerException, InvalidCredentialsException {
		Wallet wallet = wService.showBalance(key);

		return new ResponseEntity<Wallet>(wallet, HttpStatus.OK);
	}

	@PostMapping("/transfer")
	public ResponseEntity<String> fundTransferHandler(@RequestParam String to, @RequestParam double amount,
			@RequestParam String key) throws CustomerException, InsufficientFundException, InvalidCredentialsException {
		String s = wService.fundTransfer(to, amount, key);

		return new ResponseEntity<String>(s, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<Customer> updateCustomerHandler(@RequestParam String key, @RequestBody Customer customer)
			throws CustomerException, InvalidCredentialsException {
		Customer cus = wService.updateAccount(customer, key);
		return new ResponseEntity<Customer>(cus, HttpStatus.OK);
	}

	@PostMapping("/deposit")
	public ResponseEntity<Wallet> AddMoneyHandler(@RequestParam double amount, @RequestParam String key,
			@RequestBody BankAccount acc) throws CustomerException, WalletException, InvalidCredentialsException {
		Wallet wallet = wService.addMoney(key, amount, acc);
		return new ResponseEntity<Wallet>(wallet, HttpStatus.OK);
	}

}
