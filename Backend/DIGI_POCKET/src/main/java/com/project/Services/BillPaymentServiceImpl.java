package com.project.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Exceptions.InsufficientFundException;
import com.project.Exceptions.InvalidCredentialsException;
import com.project.Exceptions.WalletException;
import com.project.Model.BillPayment;
import com.project.Model.Wallet;
import com.project.Repositories.BillPaymentRepo;
import com.project.Repositories.WalletRepo;

@Service
public class BillPaymentServiceImpl implements BillPaymentService {

	@Autowired
	BillPaymentRepo billRepo;

	@Autowired
	WalletRepo walletRepo;

	// TODO Delete this method after testing.
	@Override
	public Wallet addwallet(Wallet wallet) {
		return walletRepo.save(wallet);
	}

	@Override
	public BillPayment addBillPayment(BillPayment bill, Integer wallet_Id)
			throws InvalidCredentialsException, InsufficientFundException {
		Optional<Wallet> wallet = walletRepo.findById(wallet_Id);

		// Authentication Here
		if (!wallet.isPresent())
			throw new InvalidCredentialsException("Invalid User.");

		if (wallet.get().getBalance() < bill.getAmount())
			throw new InsufficientFundException("Insufficient Funds in Wallet.");
		
		wallet.get().setBalance(wallet.get().getBalance() - bill.getAmount());

		BillPayment savedbill = billRepo.save(bill);
		savedbill.setWallet(wallet.get());

		wallet.get().getBillPayments().add(bill);

		walletRepo.save(wallet.get());

		return savedbill;

	}

	@Override
	public List<BillPayment> viewAllBillPayments(Integer wallet_Id)
			throws InvalidCredentialsException, WalletException {
		Optional<Wallet> wallet = walletRepo.findById(wallet_Id);
		if (!wallet.isPresent())
			throw new WalletException("Invalid User.");

		List<BillPayment> billList = wallet.get().getBillPayments();
		return billList;
	}

}
