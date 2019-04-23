package com.mastery.java.task.dao;

import com.mastery.java.task.dao.EmployeeDaoInterface;
import com.mastery.java.task.dao.PersistException;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao implements EmployeeDaoInterface {

    private Connection connection;
    private PreparedStatement statementCreate;
    private PreparedStatement statementUpdate;
    private PreparedStatement statementDelete;
    private PreparedStatement statementSelectID;

    public EmployeeDao(Connection connection) throws PersistException {
        try {
            this.connection = connection;
            statementCreate = connection.prepareStatement(getCreateQuery(), PreparedStatement.RETURN_GENERATED_KEYS);
            statementUpdate = connection.prepareStatement(getUpdateQuery());
            statementDelete = connection.prepareStatement(getDeleteQuery());
            statementSelectID = connection.prepareStatement(SelectIdQuery());
        } catch (SQLException e) {
            throw new PersistException("Ошибка при создании prepareStatement в классе " + getClass(), e);
        }
    }

    public void create(Employee employee) throws PersistException {
        ResultSet generatedId = null;
        try {
            prepareStatementForInsert(statementCreate, employee);
            statementCreate.executeUpdate();
            generatedId = statementCreate.getGeneratedKeys();
            if (generatedId.next()) {
                int id = generatedId.getInt(1);
                statementSelectID.setInt(1, id);
            }
        } catch (Exception e) {
            throw new PersistException(" Невозможно записать данные в БД", e);
        } finally {
            try {
                if (generatedId != null) {
                    generatedId.close();
                }
            } catch (SQLException e) {
                throw new PersistException("Ошибка закрытия потока", e);
            }
        }
    }

    public List<Employee> getAll() throws PersistException {
        ArrayList list = new ArrayList();
        ResultSet rs = null;
        try {
            PreparedStatement stm = connection.prepareStatement(getSelectAll());
            rs = stm.executeQuery();
            while (rs.next()) {
                Employee empl = new Employee();
                empl.setEmployeeId(rs.getLong(1));
                empl.setFirstName(rs.getString(2));
                empl.setLastName(rs.getString(3));
                empl.setDepartmentId(rs.getLong(4));
                empl.setJobTitle(rs.getString(5));
                empl.setGender(Gender.valueOf(rs.getString(6)));
                empl.setDateOfBirth(rs.getString(7));
                list.add(empl);
            }
        } catch (SQLException e) {
            throw new PersistException("Ошибка Sql запроса", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new PersistException("Ошибка закрытия потока", e);
            }
        }
        return list;
    }

    public void update(Employee employee) throws PersistException {
        try {
            prepareStatementForUpdate(statementUpdate, employee);
            statementUpdate.executeUpdate();
        } catch (SQLException e) {
            throw new PersistException("Ошибка Sql запроса", e);
        }
    }

    public void delete(Employee employee) throws PersistException {
        try {
            prepareStatementForDelete(statementDelete, employee);
            statementDelete.executeUpdate();
        } catch (SQLException e) {
            throw new PersistException("Ошибка Sql запроса", e);
        }
    }

    public Employee getById(int id) throws PersistException {
        Employee employee = new Employee();
        ResultSet rs = null;
        try {
            PreparedStatement stm = connection.prepareStatement(SelectIdQuery());
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                employee.setEmployeeId(rs.getLong(1));
                employee.setFirstName(rs.getString(2));
                employee.setLastName(rs.getString(3));
                employee.setDepartmentId(rs.getLong(4));
                employee.setJobTitle(rs.getString(5));
                employee.setGender(Gender.valueOf(rs.getString(6)));
                employee.setDateOfBirth(rs.getString(7));
            }
        } catch (SQLException e) {
            throw new PersistException("Ошибка обращения к БД ", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new PersistException("Ошибка закрытия потока", e);
            }
        }
        return employee;
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
        return "SELECT employee_id, first_name, last_name, department_id, job_title, gender, date_of_birth FROM employee WHERE employee_ID = ? ";
    }

    private void prepareStatementForInsert(PreparedStatement statement, Employee object) throws PersistException {
        try {
            statement.setString(1, object.getFirstName());
            statement.setString(2, object.getLastName());
            statement.setLong(3, object.getDepartmentId());
            statement.setString(4, object.getJobTitle());
            statement.setString(5, String.valueOf(object.getGender()));
            statement.setString(6, object.getDateOfBirth());
        } catch (SQLException e) {
            throw new PersistException("Ошибка получения prepareStatementForInsert", e);
        }
    }

    private void prepareStatementForUpdate(PreparedStatement statement, Employee object) throws PersistException {
        try {
            statement.setString(1, object.getFirstName());
            statement.setString(2, object.getLastName());
            statement.setLong(3, object.getDepartmentId());
            statement.setString(4, object.getJobTitle());
            statement.setString(5, String.valueOf(object.getGender()));
            statement.setString(6, object.getDateOfBirth());
            statement.setLong(7, object.getEmployeeId());
        } catch (Exception e) {
            throw new PersistException("Ошибка получения prepareStatementForUpdate", e);

        }
    }

    private void prepareStatementForDelete(PreparedStatement statement, Employee object) throws PersistException {
        try {
            statement.setLong(1, object.getEmployeeId());
        } catch (Exception e) {
            throw new PersistException("Ошибка получения prepareStatementForDelete", e);

        }
    }

    public void close() throws PersistException {
        try {
            if (statementDelete != null)
                statementDelete.close();
        } catch (SQLException e) {
            throw new PersistException("Ошибка закрытия statementDelete ", e);
        }
        try {
            if (statementCreate != null)
                statementCreate.close();
        } catch (SQLException e) {
            throw new PersistException("Ошибка закрытия statementCreate ", e);
        }
        try {
            if (statementUpdate != null)
                statementUpdate.close();
        } catch (SQLException e) {
            throw new PersistException("Ошибка закрытия statementUpdate ", e);
        }
        try {
            if (statementSelectID != null)
                statementSelectID.close();
        } catch (SQLException e) {
            throw new PersistException("Ошибка  закрытия statementSelectID ", e);
        }
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            throw new PersistException("Ошибка закрытия Connection ", e);
        }
    }

}

