package com.example.management.service;

import com.example.management.Exception.EntityNotFoundException;
import com.example.management.model.Employee;
import com.example.management.repository.EmployeeRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.action.internal.EntityActionVetoException;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeService implements IEmployeeService{

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee, Long id) {
        getEmployeeById(id);
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        getEmployeeById(id);
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() ->
                    new EntityNotFoundException(Employee.class.getSimpleName(), id));
    }

    @Override
    public List<Employee> findByPositionAndDepartment(String position, String department) {
        return employeeRepository.searchByDepartmentAndPosition(position, department);
    }

    @Override
    public List<Employee> findByPosition(String position) {
        return employeeRepository.searchByPosition(position);
    }

    @Override
    public List<Employee> findBySalaryAbove(BigDecimal salary) {
        return employeeRepository.findEmployeesWithSalaryAbove(salary);
    }
}
