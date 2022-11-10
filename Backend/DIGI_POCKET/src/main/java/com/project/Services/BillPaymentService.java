package com.project.Services;

import java.util.List;

import com.project.Exceptions.InsufficientFundException;
import com.project.Exceptions.InvalidCredentialsException;
import com.project.Exceptions.WalletException;
import com.project.Model.BillPayment;
import com.project.Model.Wallet;

public interface BillPaymentService {

	public Wallet addwallet(Wallet wallet);

	public BillPayment addBillPayment(BillPayment bill, Integer wallet_Id)
			throws InvalidCredentialsException, InsufficientFundException;
	
	public List<BillPayment> viewAllBillPayments(Integer wallet_Id) throws InvalidCredentialsException, WalletException;
	
}
