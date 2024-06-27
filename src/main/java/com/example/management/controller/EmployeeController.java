package com.example.management.controller;

import com.example.management.model.Employee;
import com.example.management.service.IEmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(path = "/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final IEmployeeService employeeService;


    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Employee.class), mediaType = "application/json")
            })
    })
    @Operation(summary = "Get all Employees", tags = {"employee", "get", "multi"})
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getEmployees(){
        return employeeService.getAllEmployees();
    }


    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Employee.class), mediaType = "application/json")
            })
    })
    @Operation(summary = "Get Employee by Id", tags = {"employee", "get", "single"})
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getEmployees(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }


    @GetMapping(path = "/position-department/{position}/{department}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> searchByPositionAndDepartment(@PathVariable String position, @PathVariable String department){

        return employeeService.findByPositionAndDepartment(position, department);
    }

    @GetMapping(path = "/position/{position}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> searchByPosition(@PathVariable String position){
        return employeeService.findByPosition(position);
    }

    @GetMapping(path = "/salary/{salary}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> saerchByHigherSalary(@PathVariable BigDecimal salary){
        return employeeService.findBySalaryAbove(salary);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = Employee.class), mediaType = "application/json")
            })
    })
    @Operation(summary = "Create Employee", tags = {"employee", "post", "single"})
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Employee.class), mediaType = "application/json")
            })
    })
    @Operation(summary = "Update Employee", tags = {"employee", "put", "single"})
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        return employeeService.updateEmployee(employee, id);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204")
    })
    @Operation(summary = "Delete Employee", tags = {"employee", "delete", "single"})
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployeeById(id);
    }

}
