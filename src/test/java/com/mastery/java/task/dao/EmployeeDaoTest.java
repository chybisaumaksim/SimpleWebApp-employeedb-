package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.service.EmployeeService;
import com.mastery.java.task.service.Service;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EmployeeDaoTest {

    static EmployeeDao employeeDao;

    @BeforeClass
    public static void setUp() throws PersistException {
        Service employeeService = new EmployeeService();
        employeeDao = employeeService.getEmployeeDao();
    }

    Employee employee = new Employee();

    @Test
    public void create() throws PersistException {
        int size = employeeDao.getAll().size();
        employee.setFirstName("Tatiana");
        employee.setLastName("Sergeeva");
        employee.setDepartmentId(3L);
        employee.setJobTitle("cook");
        employee.setGender(Gender.FEMALE);
        employee.setDateOfBirth("19670607");
        employeeDao.create(employee);
        assertEquals(size, employeeDao.getAll().size() - 1);
    }

    @Test
    public void getAll() throws PersistException {
        assertTrue(employeeDao.getAll().size() != 0);
    }

    @Test
    public void update() throws PersistException {
        Long number=(long)Math.random()*10;
        employee.setEmployeeId(129L);
        employee.setFirstName("Victor");
        employee.setLastName("Popov");
        employee.setDepartmentId(number);
        employee.setJobTitle("cheef");
        employee.setGender(Gender.MALE);
        employee.setDateOfBirth("19890101");
        employeeDao.update(employee);
        assertEquals(employee.toString(), employeeDao.getById(129L).toString());
    }


    @Test
    public void delete() {
        employee.setEmployeeId(190L);
            try {
                employeeDao.delete(employee);
            } catch (PersistException e) {
                Assert.fail();
            }
    }

    @Test
    public void getById() throws PersistException {
        employee.setEmployeeId(129L);
        employee.setFirstName("Victor");
        employee.setLastName("Popovich");
        employee.setDepartmentId(3L);
        employee.setJobTitle("cheef");
        employee.setGender(Gender.MALE);
        employee.setDateOfBirth("19890101");
        employeeDao.update(employee);
        assertEquals(employee.toString(), employeeDao.getById(129L).toString());
    }
}