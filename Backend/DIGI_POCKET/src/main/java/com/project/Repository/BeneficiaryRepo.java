package com.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Model.Beneficiary;

@Repository
public interface BeneficiaryRepo extends JpaRepository<Beneficiary, String>{

}
