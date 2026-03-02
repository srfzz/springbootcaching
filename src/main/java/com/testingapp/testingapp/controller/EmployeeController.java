package com.testingapp.testingapp.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.testingapp.testingapp.dto.EmployeeRequestDto;
import com.testingapp.testingapp.dto.EmployeeResponseDto;
import com.testingapp.testingapp.reponse.ApiResponse;
import com.testingapp.testingapp.services.contracts.EmployeeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<?>> create(@Valid @RequestBody EmployeeRequestDto employeeRequestDto) {
        EmployeeResponseDto responseDto = employeeService.save(employeeRequestDto);
        ApiResponse<?> apiResponse = ApiResponse.builder().success(true).status(HttpStatus.CREATED.value())
                .message("Employee Created Successfully !").data(responseDto).timestamp(LocalDateTime.now()).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);

    }

    @GetMapping()
    public ResponseEntity<ApiResponse<?>> getAll() {
        List<EmployeeResponseDto> responseDto = employeeService.findAll();
        ApiResponse<?> apiResponse = ApiResponse.builder().status(HttpStatus.OK.value()).success(true)
                .timestamp(LocalDateTime.now()).message("Employee data").data(responseDto).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<?>> getById(@PathVariable Long id) {
        EmployeeResponseDto responseDto = employeeService.findById(id);
        ApiResponse<?> apiResponse = ApiResponse.builder().status(HttpStatus.OK.value()).success(true)
                .timestamp(LocalDateTime.now()).message("Employee data").data(responseDto).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<?>> update(@Valid @PathVariable Long id,
            @RequestBody EmployeeRequestDto employeeRequestDto) {
        EmployeeResponseDto responseDto = employeeService.update(employeeRequestDto, id);
        ApiResponse<?> apiResponse = ApiResponse.builder().status(HttpStatus.OK.value()).success(true)
                .timestamp(LocalDateTime.now()).message("Employee Updated Successfully").data(responseDto).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<?>> updateEmployee(@PathVariable Long id,
            @RequestBody Map<String, Object> employeeRequestDto) {
        EmployeeResponseDto responseDto = employeeService.UpdateEmployee(employeeRequestDto, id);
        ApiResponse<?> apiResponse = ApiResponse.builder().status(HttpStatus.OK.value()).success(true)
                .timestamp(LocalDateTime.now()).message("Employee Updated Successfully").data(responseDto).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteById(@PathVariable Long id) {
        employeeService.delete(id);
        ApiResponse<?> apiResponse = ApiResponse.builder().status(HttpStatus.OK.value()).success(true)
                .message("Deleted Successfully").data(null).timestamp(LocalDateTime.now()).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    // method to Update the salary of employee by increment amount
    @PutMapping("/{id}/increment-salary")

    public ResponseEntity<ApiResponse<?>> incrementSalary(@PathVariable Long id, @RequestBody Double incrementAmount) {
        EmployeeResponseDto responseDto = employeeService.incrementSalary(id, incrementAmount);
        ApiResponse<?> apiResponse = ApiResponse.builder().status(HttpStatus.OK.value()).success(true)
                .timestamp(LocalDateTime.now()).message("Employee Salary Incremented Successfully").data(responseDto)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
