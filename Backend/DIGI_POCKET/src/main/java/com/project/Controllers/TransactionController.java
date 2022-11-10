package com.project.Controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Exceptions.TransactionException;
import com.project.Exceptions.WalletException;
import com.project.Model.Transaction;
import com.project.Model.Wallet;
import com.project.Services.TransactionService;

@RestController
public class TransactionController {
	
	@Autowired
	private TransactionService tService;
	
	@PostMapping("/Transactions")
	public ResponseEntity<Transaction> addTransactionHandler(@RequestBody Transaction transaction) {
		
		Transaction savedTransaction = tService.addTransaction(transaction);
		return new ResponseEntity<>(savedTransaction , HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/Transaction")
	public ResponseEntity<List<Transaction>> viewAllTransactionByWalletHandler(@RequestBody Wallet wallet)
			throws WalletException {
		List<Transaction> transactions = tService.viewAllTransaction(wallet);
		return new ResponseEntity<List<Transaction>>(transactions,HttpStatus.OK);
	}
	
	@GetMapping("/Transaction/{from}/{to}/{Id}")
	public ResponseEntity<List<Transaction>> viewTransactionByDateHandler(@PathVariable("from") Date from,
			@PathVariable("to") Date to, @PathVariable("Id") Integer walletId) throws WalletException {
		List<Transaction> transactions = tService.viewTransactionByDate(from, to, walletId);
		return new ResponseEntity<List<Transaction>>(transactions,HttpStatus.OK);
	}
	
	@GetMapping("/Transaction/{TransactionId}")
    public ResponseEntity<Transaction> viewTransactionByIdHandler(@PathVariable("TransactionId") Integer TransactionId) throws TransactionException{
		Transaction transaction = tService.viewTransaction(TransactionId);
		return new ResponseEntity<Transaction>(transaction,HttpStatus.OK);
	}
	
	
}
