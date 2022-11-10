package com.project.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Exceptions.BankAccountException;
import com.project.Model.BankAccount;
import com.project.Model.Wallet;
import com.project.Repositories.BankAccountRepo;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private BankAccountRepo repo;
	
	@Override
	public Wallet addAccount(BankAccount account) {		
		// TODO Auto-generated method stub
		return null;	
	}

	@Override
	public Wallet removeAccount(Integer accountNo) throws BankAccountException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankAccount viewAccount(Wallet wallet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BankAccount> viewAccounts(Wallet wallet) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
