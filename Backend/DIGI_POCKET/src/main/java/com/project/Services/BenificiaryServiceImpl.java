package com.project.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Exceptions.BeneficiaryNotFoundException;
import com.project.Exceptions.CustomerException;
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
	public Beneficiary addBeneficiary(Beneficiary bd,Integer walletId) throws CustomerException {
	  Optional<Wallet> opt =	walletRepo.findById(walletId);
	  Optional<Beneficiary> opt2 = bRepo.findById(bd.getMobile());
	  if(opt.isPresent() && opt2.isPresent()) {
		  Wallet wallet = opt.get();
		  List<Beneficiary> blist = wallet.getBeneficiaryList();
		  blist.add(bd);
		  opt2.get().getWalletList().add(wallet);
	  }else{
		  throw new CustomerException("customer not found..");
	  }
		return bRepo.save(opt2.get());
	}

	
	@Override
	public Beneficiary deleteBeneficiary(Beneficiary bd,Integer walletId) throws BeneficiaryNotFoundException {
		Optional<Beneficiary> opt = bRepo.findById(bd.getMobile());
		Optional<Wallet> w = walletRepo.findById(walletId);
		if(w.isPresent()) {
			Wallet w2 = w.get();
			List<Beneficiary> blist = w2.getBeneficiaryList();
			blist.removeIf(s->s.getMobile().equals(bd.getMobile()));
		}
		if (opt.isPresent()) {
			bRepo.delete(bd);
			return bd;
		} else
			throw new BeneficiaryNotFoundException("beneficiary does not exsit");

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
	public List<Beneficiary> viewAllBeneficiary(Customer customer) throws BeneficiaryNotFoundException, CustomerException {
		Optional<Customer> opt = customerRepo.findById(customer.getMobile());
		List<Beneficiary> blist = null;
		if(opt.isPresent()) {
			Customer c = opt.get();
	        Wallet w = c.getWallet();
	        blist = w.getBeneficiaryList();
		}else {
			throw new CustomerException("Customer not found");
		}
		
		if(blist.size()!=0)
			return blist;
		else
			throw new BeneficiaryNotFoundException("for this customer beneficiary not found");
	}
	
	
	

}
