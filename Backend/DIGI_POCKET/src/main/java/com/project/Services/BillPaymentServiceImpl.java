package com.project.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Exceptions.InvalidCredentialsException;
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

	@Override
	public Wallet addwallet(Wallet wallet) {
		return walletRepo.save(wallet);
	}

	@Override
	public BillPayment addBillPayment(BillPayment bill, Integer wallet_Id) throws InvalidCredentialsException {
		Optional<Wallet> wallet = walletRepo.findById(wallet_Id);

		// Authentication Here
		if (!wallet.isPresent())
			throw new InvalidCredentialsException("Invalid User.");

		BillPayment savedbill = billRepo.save(bill);
		savedbill.setWallet(wallet.get());

		wallet.get().getBillPayments().add(bill);

		walletRepo.save(wallet.get());

		return savedbill;

	}

	@Override
	public List<BillPayment> viewAllBillPayments(Integer wallet_Id) throws InvalidCredentialsException {
		// TODO Auto-generated method stub
		return null;
	}

}
