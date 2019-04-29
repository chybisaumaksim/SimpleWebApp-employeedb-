package com.mastery.java.task.service;

import com.mastery.java.task.dao.PersistException;
import org.junit.Assert;
import org.junit.Test;

public class EmployeeServiceTest {


    @Test
    public void getEmployeeDao() {
        try {
            new EmployeeService().getEmployeeDao();
        } catch (PersistException e) {
            Assert.fail();
        }
    }
}
