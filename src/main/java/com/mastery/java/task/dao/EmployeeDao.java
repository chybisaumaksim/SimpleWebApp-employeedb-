package com.mastery.java.task.dao;


import com.mastery.java.task.config.AppConfiguration;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class EmployeeDao implements Dao {

    private JdbcTemplate jdbcTemplate;

    public EmployeeDao() throws PersistException {
            jdbcTemplate = new AppConfiguration().getJdbcTemplate();
    }

    public void create(Employee employee) throws PersistException {
        jdbcTemplate.update(getCreateQuery(), employee.getFirstName(), employee.getLastName(),
                employee.getDepartmentId(), employee.getJobTitle(), String.valueOf(employee.getGender()),
                employee.getDateOfBirth());
    }

    public void delete(Employee employee) throws PersistException {
        jdbcTemplate.update(getDeleteQuery(), employee.getEmployeeId());
    }

    public List<Employee> getAll() throws PersistException {
        return jdbcTemplate.query(getSelectAll(), new EmployeeMapper());
    }

    public void update(Employee employee) throws PersistException {
        jdbcTemplate.update(getUpdateQuery(), employee.getFirstName(), employee.getLastName(),
                employee.getDepartmentId(), employee.getJobTitle(), String.valueOf(employee.getGender()),
                employee.getDateOfBirth(), employee.getEmployeeId());
    }

    public Employee getById(Long id) throws PersistException{
        return jdbcTemplate.queryForObject(SelectIdQuery(), new EmployeeMapper(), id);
    }

    private String getSelectAll() {
        return "SELECT employee_id, first_name, last_name, department_id, " +
                "job_title, gender, date_of_birth FROM employee ";
    }

    private String getCreateQuery() {
        return "INSERT INTO employee (first_name, last_name, " +
                "department_id, job_title, gender, date_of_birth) VALUES (?, ?, ?, ?, ?, ?) ; ";
    }

    private String getUpdateQuery() {
        return "UPDATE employee SET first_name = ?, last_name = ?, department_id = ?, job_title = ?, gender = ?, date_of_birth = ? WHERE employee_id = ? ";
    }

    private String getDeleteQuery() {
        return "DELETE FROM employee WHERE employee_id = ? ";
    }

    private String SelectIdQuery() {
        return "SELECT * FROM employee WHERE employee_ID = ? ";
    }

}

