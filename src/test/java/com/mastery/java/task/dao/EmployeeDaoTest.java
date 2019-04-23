package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.service.EmployeeService;
import com.mastery.java.task.service.EmployeeServiceInterface;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeDaoTest {

    static EmployeeDao employeeDao;

    @BeforeClass
    public static void setUp() throws PersistException {
        EmployeeServiceInterface employeeService = new EmployeeService();
        employeeDao = employeeService.getEmployeeDao();
    }

    Employee employee = new Employee();

    @Test
    public void create() throws PersistException {
        int size = employeeDao.getAll().size();
        employee.setFirstName("Tatiana");
        employee.setLastName("Sergeeva");
        employee.setDepartmentId(3L);
        employee.setJobTitle("kook");
        employee.setGender(Gender.FEMALE);
        employee.setDateOfBirth("19670607");
        employeeDao.create(employee);
        assertEquals(size, employeeDao.getAll().size() - 1);
    }

    @Test
    public void getAll() {

    }

    @Test
    public void update() throws PersistException {
        employee.setEmployeeId(2L);
        employee.setFirstName("Victor");
        employee.setLastName("Popov");
        employee.setDepartmentId(3L);
        employee.setJobTitle("cheef");
        employee.setGender(Gender.MALE);
        employee.setDateOfBirth("19890101");
        employeeDao.update(employee);
        assertEquals(employee.toString(), employeeDao.getById(2).toString());
    }

    @Test
    public void delete() throws PersistException {
        employee.setEmployeeId(3L);
        employeeDao.delete(employee);
        assertFalse(employeeDao.getAll().contains(employee));
    }

    @Test
    public void getById() {

    }

    @AfterClass
    public static void close() throws PersistException {
        if (employeeDao != null) {
            employeeDao.close();
        }
    }
}