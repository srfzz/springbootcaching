package com.testingapp.testingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testingapp.testingapp.entity.SalaryAccountEntity;

@Repository
public interface SalaryAccountRepository extends JpaRepository<SalaryAccountEntity,Long> {

}
