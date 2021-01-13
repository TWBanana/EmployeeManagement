package com.twbanana.springboot.crud.demo.rest;

import com.twbanana.springboot.crud.demo.entity.Employee;
import com.twbanana.springboot.crud.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Kevin on 1/11/21
 */

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    // inject EmployeeDAO
    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //expose "/employees" and return list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {

        return employeeService.findAll();
    }

    // add mapping for GET /employees/{employee}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);

        if (employee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }
        return employee;
    }

    // add mapping for POST /employees - add new employee
    @PostMapping("/employees")
    public Employee add(@RequestBody Employee employee) {

        // in case they pass an id in JSON... set id to 0
        // this is to force a save of new item... instead of update
        employee.setId(0);
        employeeService.save(employee);

        return employee;
    }

    // add mapping for PUT /employee - update existing employee
    @PutMapping("/employees")
    public Employee update(@RequestBody Employee employee) {
        employeeService.save(employee);

        return employee;
    }

    // add mapping for DELETE /employees/{employee} - delete existing employee
    @DeleteMapping("/employees/{employeeId}")
    public String delete(@PathVariable int employeeId) {

        Employee employee = employeeService.findById(employeeId);

        if (employee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);

        }

        employeeService.delete(employeeId);

        return "Employee id - " + employeeId + " has been deleted.";
    }


}
