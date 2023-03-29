package com.example.employee.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.employee.model.Employee;
import com.example.employee.model.EmployeeRowMapper;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;


@Service
public class EmployeeH2Service{

    @Autowired
    private JdbcTemplate db;

    public ArrayList<Employee> getEmployees() {
       return (ArrayList<Employee>) db.query("SELECT * FROM employeelist",new EmployeeRowMapper());
    }
    public Employee getEmployeeById(int employeeId){
        try{
      return db.queryForObject("SELECT * FROM employeelist where employeeId=?",new EmployeeRowMapper(),employeeId);
    }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    public Employee addEmployee(Employee employee){
        db.update("INSERT INTO employeelist(employeeName,email,department) values(?,?,?)",employee.getEmployeeName(),employee.getEmail(),employee.getDepartment());
        return db.queryForObject("select * from employeelist where employeeName=? and email=?",new EmployeeRowMapper(),employee.getEmployeeName(),employee.getEmail());
    }
    public Employee updateEmployee(int employeeId,Employee employee){
        if(employee.getEmployeeName()!=null){
            db.update("Update employeelist set employeeName=? where employeeId=?",employee.getEmployeeName(),employeeId);
        }
         if(employee.getEmail()!=null){
            db.update("Update employeelist set email=? where employeeId=?",employee.getEmail(),employeeId);
        }
         if(employee.getDepartment()!=null){
            db.update("Update employeelist set department=? where employeeId=?",employee.getDepartment(),employeeId);
        }
       return getEmployeeById(employeeId);
    }

    public void deleteEmployee(int employeeId){
      db.update("delete from employeelist where employeeId=?",employeeId);
    }
}