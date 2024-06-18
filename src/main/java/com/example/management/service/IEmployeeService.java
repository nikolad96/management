package com.example.management.service;

import com.example.management.model.Employee;

import java.util.List;

public interface IEmployeeService {
    public Employee saveEmployee(Employee employee);
    public Employee updateEmployee(Employee employee, Long id);
    public void deleteEmployeeById(Long id);
    public List<Employee> getAllEmployees();
    public Employee getEmployeeById(Long id);
}
