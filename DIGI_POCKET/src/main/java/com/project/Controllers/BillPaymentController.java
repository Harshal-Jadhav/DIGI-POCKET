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

import com.project.Exceptions.InsufficientFundException;
import com.project.Exceptions.InvalidCredentialsException;
import com.project.Exceptions.WalletException;
import com.project.Model.BillPayment;
import com.project.Repositories.CustomerRepo;
import com.project.Repositories.TransactionRepo;
import com.project.Repositories.WalletRepo;
import com.project.Services.BillPaymentService;

@RestController
@RequestMapping("/digipocket/billservice")
public class BillPaymentController {

	@Autowired
	BillPaymentService billservice;

	@Autowired
	WalletRepo wr;

	@Autowired
	CustomerRepo cr;

	@Autowired
	TransactionRepo tr;

	@PostMapping("/add")
	public ResponseEntity<BillPayment> addNewBillHandler(@RequestParam String key, @RequestBody BillPayment bill)
			throws InvalidCredentialsException, InsufficientFundException {
		BillPayment savedBill = billservice.addBillPayment(bill, key);
		return new ResponseEntity<BillPayment>(savedBill, HttpStatus.CREATED);
	}

	@GetMapping("/viewall")
	public ResponseEntity<List<BillPayment>> getAllBillsHandler(@RequestParam String key)
			throws InvalidCredentialsException, WalletException {
		List<BillPayment> billList = billservice.viewAllBillPayments(key);
		return new ResponseEntity<List<BillPayment>>(billList, HttpStatus.OK);
	}
}
