package com.twbanana.springboot.crud.demo.dao;

import com.twbanana.springboot.crud.demo.entity.Employee;

import java.util.List;

/**
 * Created by Kevin on 1/11/21
 */

public interface EmployeeDAO {

    public List<Employee> findAll();

    public Employee findById(int id);

    public void save(Employee employee);

    public void deleteById(int id);
}
