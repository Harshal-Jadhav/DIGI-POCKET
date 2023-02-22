package com.project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Model.BillPayment;

@Repository
public interface BillPaymentRepo extends JpaRepository<BillPayment, Integer> {

}
