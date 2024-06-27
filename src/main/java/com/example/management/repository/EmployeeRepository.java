package com.example.management.repository;

import com.example.management.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e WHERE e.salary > :salary")
    List<Employee> findEmployeesWithSalaryAbove(BigDecimal salary);

    @Query("SELECT e FROM Employee e WHERE e.position like :position")
    List<Employee> searchByPosition(@Param("position") String position);

    @Query(value = "SELECT * FROM Employee WHERE position = ?1 AND department = ?2", nativeQuery = true)
    List<Employee> searchByDepartmentAndPosition(@Param("position") String position, @Param("department") String department);
}
