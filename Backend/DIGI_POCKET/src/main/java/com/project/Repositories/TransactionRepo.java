package com.project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Model.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

}
