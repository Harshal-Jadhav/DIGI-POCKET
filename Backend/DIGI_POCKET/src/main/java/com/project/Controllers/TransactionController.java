package com.project.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.Exceptions.InvalidCredentialsException;
import com.project.Exceptions.TransactionException;
import com.project.Exceptions.WalletException;
import com.project.Model.Transaction;
import com.project.Model.TransactionDTO;
import com.project.Services.TransactionService;

@RestController
@RequestMapping("/digipocket/transactionservice")
public class TransactionController {

	@Autowired
	private TransactionService tService;

	@GetMapping("/viewall")
	public ResponseEntity<List<Transaction>> getAllTransactionsHandler(@RequestParam String key)
			throws WalletException, InvalidCredentialsException {
		List<Transaction> tranList = tService.viewAllTransaction(key);
		return new ResponseEntity<List<Transaction>>(tranList, HttpStatus.OK);
	}

	@PostMapping("/viewall")
	public ResponseEntity<List<Transaction>> getAllTransactionsByDateHandler(@RequestParam String key,
			@RequestBody TransactionDTO tran) throws WalletException, InvalidCredentialsException {
		List<Transaction> tranList = tService.viewTransactionByDate(tran.getFrom(), tran.getTo(), key);
		return new ResponseEntity<List<Transaction>>(tranList, HttpStatus.OK);
	}

	@GetMapping("/view")
	public ResponseEntity<Transaction> getTransactionHandler(@RequestParam Integer tran, @RequestParam String key)
			throws TransactionException, InvalidCredentialsException {
		Transaction transaction = tService.viewTransaction(key, tran);
		return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
	}

}
