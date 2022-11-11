package com.project.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Exceptions.BankAccountException;
import com.project.Exceptions.WalletException;
import com.project.Model.BankAccount;
import com.project.Model.Wallet;
import com.project.Services.AccountService;

@RestController
@RequestMapping("/digipocket/bankaccount")
public class BankAccountController {

	@Autowired
	private AccountService accService;

	@PostMapping("/add/{walletId}")
	public ResponseEntity<Wallet> addNewBankAccount(@PathVariable("walletId") Integer wallet_Id,
			@RequestBody BankAccount account) throws BankAccountException {
		Wallet wallet = accService.addAccount(account, wallet_Id);
		return new ResponseEntity<Wallet>(wallet, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{walletId}")
	public ResponseEntity<Wallet> removeBankAccount(@PathVariable("walletId") Integer wallet_Id,
			@RequestBody BankAccount account) throws BankAccountException, WalletException {
		Wallet wallet = accService.removeAccount(account.getAccountNo(), wallet_Id);
		return new ResponseEntity<Wallet>(wallet, HttpStatus.OK);
	}
	
	@GetMapping("/view/{walletId}")
	public ResponseEntity<List<BankAccount>> getAllBankAccount(@PathVariable("walletId") Integer wallet_Id)
			throws BankAccountException {
		List<BankAccount> accList = accService.viewAccounts(wallet_Id);
		return new ResponseEntity<List<BankAccount>>(accList, HttpStatus.OK);
	}
}
