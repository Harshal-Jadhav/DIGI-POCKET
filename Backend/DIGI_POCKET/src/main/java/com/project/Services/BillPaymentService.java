package com.project.Services;

import java.util.List;

import com.project.Exceptions.InsufficientFundException;
import com.project.Exceptions.InvalidCredentialsException;
import com.project.Exceptions.WalletException;
import com.project.Model.BillPayment;

public interface BillPaymentService {

	public BillPayment addBillPayment(BillPayment bill, String key)
			throws InvalidCredentialsException, InsufficientFundException;

	public List<BillPayment> viewAllBillPayments(String key) throws InvalidCredentialsException, WalletException;

}
