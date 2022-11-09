package com.project.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.Exceptions.BeneficiaryNotFoundException;
import com.project.Model.Beneficiary;
import com.project.Model.Customer;
import com.project.Repositories.BeneficiaryRepo;

public class BenificiaryServiceImpl implements BenificiaryService {

	@Autowired
	private BeneficiaryRepo bRepo;

	@Override
	public Beneficiary addBeneficiary(Beneficiary bd) {
		return bRepo.save(bd);
	}

	@Override
	public Beneficiary deleteBeneficiary(Beneficiary bd) throws BeneficiaryNotFoundException {
		Optional<Beneficiary> opt = bRepo.findById(bd.getMobile());
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
	public List<Beneficiary> viewAllBeneficiary(Customer customer) throws BeneficiaryNotFoundException {
		List<Beneficiary> blist = bRepo.findAll();
		if (blist.size() != 0)
			return blist;
		else
			throw new BeneficiaryNotFoundException("no beneficiary found");
	}

}
