package com.project.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Exceptions.BeneficiaryNotFoundException;
import com.project.Exceptions.CustomerException;
import com.project.Exceptions.WalletException;
import com.project.Model.Beneficiary;
import com.project.Model.Customer;
import com.project.Model.Wallet;
import com.project.Repositories.BeneficiaryRepo;
import com.project.Repositories.CustomerRepo;
import com.project.Repositories.WalletRepo;

@Service
public class BenificiaryServiceImpl implements BenificiaryService {

	@Autowired
	private BeneficiaryRepo bRepo;

	@Autowired
	private WalletRepo walletRepo;

	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public Beneficiary addBeneficiary(Beneficiary bd, Integer walletId) throws CustomerException, WalletException {
		Optional<Customer> customer = customerRepo.findById(bd.getMobile());
		Optional<Wallet> wallet = walletRepo.findById(walletId);

		if (!customer.isPresent())
			throw new CustomerException("Beneficiary is not Registered to the Application.");

		if (!wallet.isPresent())
			throw new WalletException("Invalid User.");

		Optional<Beneficiary> ben = bRepo.findById(bd.getMobile());

		if (ben.isPresent()) {
			ben.get().getWalletList().add(wallet.get());
			wallet.get().getBeneficiaryList().add(ben.get());
			return bRepo.save(ben.get());
		} else {
			bd.getWalletList().add(wallet.get());
			return bRepo.save(bd);
		}
	}

	@Override
	public Beneficiary deleteBeneficiary(Beneficiary bd, Integer walletId)
			throws BeneficiaryNotFoundException, WalletException {
		Optional<Beneficiary> ben = bRepo.findById(bd.getMobile());
		Optional<Wallet> wallet = walletRepo.findById(walletId);

		if (!ben.isPresent())
			throw new BeneficiaryNotFoundException("Invalid Beneficiary Details.");

		if (!wallet.isPresent())
			throw new WalletException("Invalid User.");

		boolean flag = false;
		List<Beneficiary> benList = wallet.get().getBeneficiaryList();

		for (Beneficiary b : benList) {
			if (b.getMobile().equals(b.getMobile())) {
				flag = true;
				break;
			}
		}

		if (!flag)
			throw new BeneficiaryNotFoundException("Beneficiary not linked with this account.");

		List<Wallet> walletList = ben.get().getWalletList();

		walletList.removeIf(w -> w.getWalletId().equals(walletId));
		benList.removeIf(b -> b.getMobile().equals(bd.getMobile()));

		walletRepo.save(wallet.get());
		return bRepo.save(ben.get());
	}

	@Override
	public Beneficiary viewBeneficiary(String mobile) throws BeneficiaryNotFoundException {
		Optional<Beneficiary> opt = bRepo.findById(mobile);
		if (opt.isPresent())
			return opt.get();
		else
			throw new BeneficiaryNotFoundException("Beneficiary not found");
	}

	@Override
	public List<Beneficiary> viewAllBeneficiary(Customer customer)
			throws BeneficiaryNotFoundException, CustomerException {
		Optional<Customer> opt = customerRepo.findById(customer.getMobile());
		List<Beneficiary> blist = null;
		if (opt.isPresent()) {
			Customer c = opt.get();
			Wallet w = c.getWallet();
			blist = w.getBeneficiaryList();
		} else {
			throw new CustomerException("Customer not found");
		}

		if (blist.size() != 0)
			return blist;
		else
			throw new BeneficiaryNotFoundException("for this customer beneficiary not found");
	}

}
