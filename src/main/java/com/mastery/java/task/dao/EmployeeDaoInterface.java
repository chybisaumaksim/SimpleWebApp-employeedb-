package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;

import java.util.List;

/**
 * @author Maksim Chybisau
 * @project Project
 */
public interface EmployeeDaoInterface {

    List<Employee> getAll() throws PersistException;

    void create(Employee employee) throws PersistException;

    void update(Employee employee) throws PersistException;

    void delete(Employee employee) throws PersistException;

    Employee getById(int id) throws PersistException;

    void close() throws PersistException;

}
