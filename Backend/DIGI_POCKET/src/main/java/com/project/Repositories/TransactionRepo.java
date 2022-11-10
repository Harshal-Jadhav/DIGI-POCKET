package com.project.Repositories;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.Model.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

	@Query("from Transaction t where t.transactionDate > :from and t.transactionDate < :to")
	public List<Transaction> getTransactionsByDate(@Param("from")LocalDate from,@Param("to") LocalDate to);
	
	@Query("SELECT t FROM com.project.Transaction t WHERE t.transactionDate>=?1 AND t.transactionDate<=?2 AND t.wallet_Id=?3")
	public List<Transaction> getAllTransactionByDate(Date d1, Date d2, Integer walletId);
}
