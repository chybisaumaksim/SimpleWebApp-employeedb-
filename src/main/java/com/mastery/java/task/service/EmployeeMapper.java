package com.mastery.java.task.service;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Maksim Chybisau
 * @project Project
 */
public class EmployeeMapper implements RowMapper<Employee> {

    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeId(resultSet.getLong("employee_Id"));
        employee.setFirstName(resultSet.getString("first_name"));
        employee.setLastName(resultSet.getString("last_name"));
        employee.setDepartmentId(resultSet.getLong("department_id"));
        employee.setJobTitle(resultSet.getString("job_title"));
        employee.setGender(Gender.valueOf(resultSet.getString("gender")));
        employee.setDateOfBirth(resultSet.getString("date_Of_birth"));
        return employee;
    }
}
