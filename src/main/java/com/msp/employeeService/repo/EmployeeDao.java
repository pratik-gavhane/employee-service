package com.msp.employeeService.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msp.employeeService.entity.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {

}
