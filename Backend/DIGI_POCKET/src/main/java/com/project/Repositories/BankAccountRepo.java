package com.project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Model.BankAccount;

@Repository
public interface BankAccountRepo extends JpaRepository<BankAccount, Integer> {

}
