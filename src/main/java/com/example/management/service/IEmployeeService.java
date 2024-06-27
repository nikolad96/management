package com.example.management.service;

import com.example.management.model.Employee;

import java.math.BigDecimal;
import java.util.List;

public interface IEmployeeService {
    Employee saveEmployee(Employee employee);
    Employee updateEmployee(Employee employee, Long id);
    void deleteEmployeeById(Long id);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    List<Employee> findByPositionAndDepartment(String position, String department);
    List<Employee> findByPosition(String position);

    List<Employee> findBySalaryAbove(BigDecimal salary);

}
