package com.testingapp.testingapp.repository;

import java.util.Optional;

import com.testingapp.testingapp.entity.EmployeeEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import jakarta.persistence.LockModeType;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    boolean existsByEmail(String email);

    Optional<EmployeeEntity> findByEmail(String email);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<EmployeeEntity> findById(Long id);

}
