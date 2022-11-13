package com.project.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Exceptions.InsufficientFundException;
import com.project.Exceptions.InvalidCredentialsException;
import com.project.Exceptions.WalletException;
import com.project.Model.BillPayment;
import com.project.Model.CurrentUserSession;
import com.project.Model.Customer;
import com.project.Model.Wallet;
import com.project.Repositories.BillPaymentRepo;
import com.project.Repositories.CustomerRepo;
import com.project.Repositories.SessionRepo;
import com.project.Repositories.WalletRepo;

@Service
public class BillPaymentServiceImpl implements BillPaymentService {

	@Autowired
	private BillPaymentRepo billRepo;

	@Autowired
	private WalletRepo walletRepo;

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private SessionRepo session;

	@Override
	public BillPayment addBillPayment(BillPayment bill, String key)
			throws InvalidCredentialsException, InsufficientFundException {
		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new InvalidCredentialsException("Invalid Session key.");
		Optional<Customer> customer = customerRepo.findById(currSession.getMobile());
		Optional<Wallet> wallet = walletRepo.findById(customer.get().getWallet().getWalletId());

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
	public List<BillPayment> viewAllBillPayments(String key) throws InvalidCredentialsException, WalletException {
		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new InvalidCredentialsException("Invalid Session key.");

		Optional<Customer> customer = customerRepo.findById(currSession.getMobile());
		Optional<Wallet> wallet = walletRepo.findById(customer.get().getWallet().getWalletId());

		if (!wallet.isPresent())
			throw new WalletException("Invalid User.");

		List<BillPayment> billList = wallet.get().getBillPayments();

		if (billList.size() == 0)
			throw new WalletException("No Bill Found.");

		return billList;
	}

}
