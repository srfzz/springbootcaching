package com.testingapp.testingapp.services.Impl;

import com.testingapp.testingapp.entity.EmployeeEntity;
import com.testingapp.testingapp.entity.SalaryAccountEntity;
import com.testingapp.testingapp.repository.SalaryAccountRepository;
import com.testingapp.testingapp.services.contracts.SalaryAccountService;

import org.springframework.stereotype.Service;

@Service
public class SalaryAccountServiceImpl implements SalaryAccountService {

    private final SalaryAccountRepository salaryAccountRepository;

    public SalaryAccountServiceImpl(SalaryAccountRepository salaryAccountRepository) {
        this.salaryAccountRepository = salaryAccountRepository;
    }

    @Override
    public void createSalaryAccountForEmployee(EmployeeEntity employee) {
        // if (employee.getFirstName().equalsIgnoreCase("sarfaraj")) {
        // throw new RuntimeException("Cannot create salary account for employee with
        // name sarfaraj");
        // }
        SalaryAccountEntity salaryAccount = SalaryAccountEntity.builder()
                .employee(employee)
                .build();
        salaryAccountRepository.save(salaryAccount);

    }

}
