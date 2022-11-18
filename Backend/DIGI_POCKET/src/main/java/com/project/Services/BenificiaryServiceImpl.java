package com.project.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Exceptions.BeneficiaryNotFoundException;
import com.project.Exceptions.CustomerException;
import com.project.Exceptions.InvalidCredentialsException;
import com.project.Exceptions.WalletException;
import com.project.Model.Beneficiary;
import com.project.Model.CurrentUserSession;
import com.project.Model.Customer;
import com.project.Model.Wallet;
import com.project.Repositories.BeneficiaryRepo;
import com.project.Repositories.CustomerRepo;
import com.project.Repositories.SessionRepo;
import com.project.Repositories.WalletRepo;

@Service
public class BenificiaryServiceImpl implements BenificiaryService {

	@Autowired
	private BeneficiaryRepo bRepo;

	@Autowired
	private WalletRepo walletRepo;

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private SessionRepo session;

	@Override
	public Beneficiary addBeneficiary(Beneficiary bd, String key)
			throws CustomerException, WalletException, InvalidCredentialsException {
		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new InvalidCredentialsException("Invalid Session key.");
		Optional<Customer> customer = customerRepo.findById(currSession.getMobile());
		Optional<Wallet> wallet = walletRepo.findById(customer.get().getWallet().getWalletId());

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
	public Beneficiary deleteBeneficiary(Beneficiary bd, String key)
			throws BeneficiaryNotFoundException, WalletException, InvalidCredentialsException {
		Optional<Beneficiary> ben = bRepo.findById(bd.getMobile());
		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new InvalidCredentialsException("Invalid Session key.");
		Optional<Customer> customer = customerRepo.findById(currSession.getMobile());
		Optional<Wallet> wallet = walletRepo.findById(customer.get().getWallet().getWalletId());

		if (!ben.isPresent())
			throw new BeneficiaryNotFoundException("Invalid Beneficiary Details.");

		if (!wallet.isPresent())
			throw new WalletException("Invalid User.");

		boolean flag = false;
		List<Beneficiary> benList = wallet.get().getBeneficiaryList();

		for (Beneficiary b : benList) {
			if (b.getMobile().equals(ben.get().getMobile())) {
				flag = true;
				break;
			}
		}

		if (!flag)
			throw new BeneficiaryNotFoundException("Beneficiary not linked with this account.");

		List<Wallet> walletList = ben.get().getWalletList();

		walletList.removeIf(w -> w.getWalletId().equals(wallet.get().getWalletId()));
		benList.removeIf(b -> b.getMobile().equals(bd.getMobile()));

		walletRepo.save(wallet.get());
		return bRepo.save(ben.get());
	}

	@Override
	public Beneficiary viewBeneficiary(String mobile, String key)
			throws BeneficiaryNotFoundException, InvalidCredentialsException {

		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new InvalidCredentialsException("Invalid Session key.");
		Optional<Customer> customer = customerRepo.findById(currSession.getMobile());
		Optional<Wallet> wallet = walletRepo.findById(customer.get().getWallet().getWalletId());
		List<Beneficiary> benList = wallet.get().getBeneficiaryList();

		for (Beneficiary ben : benList) {
			if (ben.getMobile().equals(mobile)) {
				return ben;
			}
		}

		throw new BeneficiaryNotFoundException("Beneficiary not found");
	}

	@Override
	public List<Beneficiary> viewAllBeneficiary(String key)
			throws BeneficiaryNotFoundException, InvalidCredentialsException {
		CurrentUserSession currSession = session.findByKey(key);

		if (currSession == null)
			throw new InvalidCredentialsException("Invalid Session key.");
		Optional<Customer> customer = customerRepo.findById(currSession.getMobile());
		Optional<Wallet> wallet = walletRepo.findById(customer.get().getWallet().getWalletId());
		List<Beneficiary> blist = wallet.get().getBeneficiaryList();

		if (blist.size() != 0)
			return blist;
		else
			throw new BeneficiaryNotFoundException("No Beneficiaries Found.");
	}

}
