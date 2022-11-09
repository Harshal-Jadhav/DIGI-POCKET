package com.project.Services;

import java.util.List;

import com.project.Exceptions.BeneficiaryNotFoundException;
import com.project.Model.Beneficiary;
import com.project.Model.Customer;

public interface BenificiaryService {

	public Beneficiary addBeneficiary(Beneficiary bd);

	public Beneficiary deleteBeneficiary(Beneficiary bd) throws BeneficiaryNotFoundException;

	public Beneficiary viewBeneficiary(String mobile) throws BeneficiaryNotFoundException;

	public List<Beneficiary> viewAllBeneficiary(Customer customer) throws BeneficiaryNotFoundException;
}
