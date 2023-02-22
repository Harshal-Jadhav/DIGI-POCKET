package com.project.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.Exceptions.BeneficiaryNotFoundException;
import com.project.Exceptions.CustomerException;
import com.project.Exceptions.InvalidCredentialsException;
import com.project.Exceptions.WalletException;
import com.project.Model.Beneficiary;
import com.project.Services.BenificiaryService;

@RestController
@RequestMapping("/digipocket/beneficiaryservice")
public class BeneficiaryController {

	@Autowired
	private BenificiaryService benService;

	@PostMapping("/add")
	public ResponseEntity<Beneficiary> addNewBeneficiaryHandler(@RequestParam String key, @RequestBody Beneficiary benf)
			throws CustomerException, WalletException, InvalidCredentialsException {
		Beneficiary ben = benService.addBeneficiary(benf, key);
		return new ResponseEntity<Beneficiary>(ben, HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Beneficiary> deleteBeneficiaryHandler(@RequestParam String key, @RequestBody Beneficiary benf)
			throws BeneficiaryNotFoundException, WalletException, InvalidCredentialsException {
		Beneficiary ben = benService.deleteBeneficiary(benf, key);
		return new ResponseEntity<Beneficiary>(ben, HttpStatus.OK);
	}

	@GetMapping("/view")
	public ResponseEntity<Beneficiary> viewBeneficiaryHandler(@RequestParam String mobile_no,
			@RequestParam String key)
			throws BeneficiaryNotFoundException, InvalidCredentialsException {
		Beneficiary ben = benService.viewBeneficiary(mobile_no, key);
		return new ResponseEntity<Beneficiary>(ben, HttpStatus.OK);
	}

	@GetMapping("/viewall")
	public ResponseEntity<List<Beneficiary>> getAllBeneficiaryHandler(@RequestParam String key)
			throws BeneficiaryNotFoundException, CustomerException, InvalidCredentialsException {
		List<Beneficiary> benList = benService.viewAllBeneficiary(key);
		return new ResponseEntity<List<Beneficiary>>(benList, HttpStatus.OK);
	}
}
