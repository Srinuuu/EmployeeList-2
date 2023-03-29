package com.example.employee.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

import com.example.employee.service.EmployeeH2Service;
import com.example.employee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class EmployeeController {
     
    @Autowired
    public EmployeeH2Service employeeService;
   
    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable("employeeId") int employeeId){
        employeeService.deleteEmployee(employeeId);
    }

    @PutMapping("/employees/{employeeId}")
    public Employee updateEmployee(@PathVariable("employeeId") int employeeId, @RequestBody Employee employee) {
        return employeeService.updateEmployee(employeeId, employee);
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @GetMapping("/employees")
    public ArrayList<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable("employeeId") int employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }
}