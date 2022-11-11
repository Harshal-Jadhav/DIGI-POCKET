package com.project.Controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Exceptions.TransactionException;
import com.project.Exceptions.WalletException;
import com.project.Model.Transaction;
import com.project.Services.TransactionService;

@RestController
@RequestMapping("/digipocket/transactions")
public class TransactionController {

	@Autowired
	private TransactionService tService;

	@GetMapping("viewAll/{walletId}")
	public ResponseEntity<List<Transaction>> getAllTransactions(@PathVariable("walletId") Integer wallet_Id)
			throws WalletException {
		List<Transaction> tranList = tService.viewAllTransaction(wallet_Id);
		return new ResponseEntity<List<Transaction>>(tranList, HttpStatus.OK);
	}

//	take input form the reqbody.
	@GetMapping("viewAll/{from}/{to}/{walletId}")
	public ResponseEntity<List<Transaction>> getAllTransactionsByDate(@PathVariable("from") Date from,
			@PathVariable("to") Date to, @PathVariable("walletId") Integer wallet_Id) throws WalletException {
		List<Transaction> tranList = tService.viewTransactionByDate(from, to, wallet_Id);
		return new ResponseEntity<List<Transaction>>(tranList, HttpStatus.OK);
	}

	@GetMapping("view/{tranId}")
	public ResponseEntity<Transaction> getTransaction(@PathVariable("tranId") Integer tran_Id)
			throws TransactionException {
		Transaction tran = tService.viewTransaction(tran_Id);
		return new ResponseEntity<Transaction>(tran, HttpStatus.OK);
	}

}
