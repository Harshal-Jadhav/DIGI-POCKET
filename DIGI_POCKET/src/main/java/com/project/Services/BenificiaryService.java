package com.project.Services;

import java.util.List;

import com.project.Exceptions.BeneficiaryNotFoundException;
import com.project.Exceptions.CustomerException;
import com.project.Exceptions.InvalidCredentialsException;
import com.project.Exceptions.WalletException;
import com.project.Model.Beneficiary;

public interface BenificiaryService {

	public Beneficiary addBeneficiary(Beneficiary bd, String key)
			throws CustomerException, WalletException, InvalidCredentialsException;

	public Beneficiary deleteBeneficiary(Beneficiary bd, String key)
			throws BeneficiaryNotFoundException, WalletException, InvalidCredentialsException;

	public Beneficiary viewBeneficiary(String mobile, String key)
			throws BeneficiaryNotFoundException, InvalidCredentialsException;

	public List<Beneficiary> viewAllBeneficiary(String key)
			throws BeneficiaryNotFoundException, InvalidCredentialsException;

}
