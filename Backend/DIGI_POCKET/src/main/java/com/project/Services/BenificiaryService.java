package com.project.Services;

import java.util.List;

import com.project.Exceptions.BeneficiaryNotFoundException;
import com.project.Exceptions.CustomerException;
import com.project.Exceptions.WalletException;
import com.project.Model.Beneficiary;
import com.project.Model.Customer;

public interface BenificiaryService {

	public Beneficiary addBeneficiary(Beneficiary bd, Integer walletId) throws CustomerException, WalletException;

	public Beneficiary deleteBeneficiary(Beneficiary bd, Integer walletId)
			throws BeneficiaryNotFoundException, WalletException;

	public Beneficiary viewBeneficiary(String mobile) throws BeneficiaryNotFoundException;

	public List<Beneficiary> viewAllBeneficiary(Customer customer) throws BeneficiaryNotFoundException, CustomerException;

}
