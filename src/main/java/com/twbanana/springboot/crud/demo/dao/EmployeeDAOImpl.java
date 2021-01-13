package com.twbanana.springboot.crud.demo.dao;

import com.twbanana.springboot.crud.demo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Kevin on 1/11/21
 */
@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    private EntityManager entityManager;

    // @Autowired is optional if using the constructor injection
    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Remove @Transactional
    @Override
    public List<Employee> findAll() {

        // get the current hibernate session
        Session session = entityManager.unwrap(Session.class);

        // create a query
        Query<Employee> query = session.createQuery("from Employee", Employee.class);

        // return the results
        return query.getResultList();
    }

    @Override
    public Employee findById(int id) {

        Session session = entityManager.unwrap(Session.class);

        Employee employee = session.get(Employee.class, id);

        return employee;
    }

    @Override
    public void save(Employee employee) {

        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(employee);
    }

    @Override
    public void deleteById(int id) {

        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("delete from Employee where id=:employeeId");

        query.setParameter("employeeId", id);

        query.executeUpdate();
    }
}
