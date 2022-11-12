package com.project.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Exceptions.InsufficientFundException;
import com.project.Exceptions.InvalidCredentialsException;
import com.project.Exceptions.WalletException;
import com.project.Model.BillPayment;
import com.project.Model.Customer;
import com.project.Model.Wallet;
import com.project.Repositories.CustomerRepo;
import com.project.Repositories.TransactionRepo;
import com.project.Repositories.WalletRepo;
import com.project.Services.BillPaymentService;

@RestController
@RequestMapping("/digipocket/bill")
public class BillPaymentController {

	@Autowired
	BillPaymentService billservice;

	@Autowired
	WalletRepo wr;

	@Autowired
	CustomerRepo cr;

	@Autowired
	TransactionRepo tr;

	@GetMapping("/create/{amount}")
	public Wallet createWallet(@PathVariable("amount") double amount) {
		Wallet w = new Wallet(amount);
		Customer c = new Customer("Harshal", "8149382368", "Harshal@6342");
		w.setCustomer(c);
		c.setWallet(w);
		Wallet wallet = wr.save(w);
		cr.save(c);
		return wallet;
	}
	
	@GetMapping("/{Id}")
	public Wallet getWallet(@PathVariable("Id") Integer Id) {
		Optional<Wallet> wallet = wr.findById(Id);
		return wallet.get();
	}


	@PostMapping("/add/{walletId}")
	public ResponseEntity<BillPayment> addNewBill(@PathVariable("walletId") Integer wallet_Id,
			@RequestBody BillPayment bill) throws InvalidCredentialsException, InsufficientFundException {
		BillPayment savedBill = billservice.addBillPayment(bill, wallet_Id);
		return new ResponseEntity<BillPayment>(savedBill, HttpStatus.CREATED);
	}
	
	
	public ResponseEntity<List<BillPayment>> getAllBills(@PathVariable("walletId") Integer wallet_Id)
			throws InvalidCredentialsException, WalletException {
		List<BillPayment> billList = billservice.viewAllBillPayments(wallet_Id);
		return new ResponseEntity<List<BillPayment>>(billList, HttpStatus.OK);
	}
}
