package com.mastery.java.task.dao;

import com.mastery.java.task.service.EmployeeDao;

public interface EmployeeServiceInterface {

    EmployeeDao getEmployeeDao() throws PersistException;
}
