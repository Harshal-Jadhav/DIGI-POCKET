package com.project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Exceptions.InvalidCredentialsException;
import com.project.Model.BillPayment;
import com.project.Model.Wallet;
import com.project.Services.BillPaymentService;

@RestController
@RequestMapping("/billpayment")
public class BillPaymentController {

	@Autowired
	BillPaymentService billservice;
	
	@PostMapping("/addwallet")
	public ResponseEntity<Wallet> addWallet(@RequestBody Wallet wallet) {
		Wallet savedWallet = billservice.addwallet(wallet);
		return new ResponseEntity<Wallet>(savedWallet,HttpStatus.CREATED);
	}
	
	@PostMapping("/addbill/{wallet_Id}")
	public ResponseEntity<BillPayment> addBill(@PathVariable("wallet_Id") Integer Id, @RequestBody BillPayment bill)
			throws InvalidCredentialsException {
		BillPayment savedbill = billservice.addBillPayment(bill, Id);
		return new ResponseEntity<BillPayment>(savedbill, HttpStatus.ACCEPTED);
	}
}
