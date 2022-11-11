package com.project.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Exceptions.BeneficiaryNotFoundException;
import com.project.Exceptions.CustomerException;
import com.project.Exceptions.WalletException;
import com.project.Model.Beneficiary;
import com.project.Model.Customer;
import com.project.Services.BenificiaryService;

@RestController
@RequestMapping("/digipocket/beneficiary")
public class BeneficiaryController {

	@Autowired
	private BenificiaryService benService;

	
	@PostMapping("/add/{walletId}")
	public ResponseEntity<Beneficiary> addNewBeneficiary(@PathVariable("walletId") Integer wallet_Id,
			@RequestBody Beneficiary benf) throws CustomerException, WalletException {
		Beneficiary ben = benService.addBeneficiary(benf, wallet_Id);
		return new ResponseEntity<Beneficiary>(ben, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{walletId}")
	public ResponseEntity<Beneficiary> deleteBeneficiary(@PathVariable("walletId") Integer wallet_Id,
			@RequestBody Beneficiary benf) throws BeneficiaryNotFoundException, WalletException {
		Beneficiary ben = benService.deleteBeneficiary(benf, wallet_Id);
		return new ResponseEntity<Beneficiary>(ben, HttpStatus.OK);
	}
	
	@GetMapping("/view/{mob}")
	public ResponseEntity<Beneficiary> viewBeneficiary(@PathVariable("mob") String mobile_no)
			throws BeneficiaryNotFoundException {
		Beneficiary ben = benService.viewBeneficiary(mobile_no);
		return new ResponseEntity<Beneficiary>(ben, HttpStatus.OK);
	}
	
	@GetMapping("view")
	public ResponseEntity<List<Beneficiary>> getAllBeneficiary(@RequestBody Customer customer)
			throws BeneficiaryNotFoundException, CustomerException {
		List<Beneficiary> benList = benService.viewAllBeneficiary(customer);
		return new ResponseEntity<List<Beneficiary>>(benList, HttpStatus.OK);
	}
}
