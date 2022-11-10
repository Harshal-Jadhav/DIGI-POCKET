package com.project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Model.Customer;
import com.project.Model.Wallet;

@Repository
public interface WalletRepo extends JpaRepository<Wallet, Integer> {

	void save(Customer customer);

}
