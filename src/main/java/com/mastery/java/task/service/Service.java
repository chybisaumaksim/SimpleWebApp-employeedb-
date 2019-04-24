package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dao.PersistException;

public interface Service {

    EmployeeDao getEmployeeDao() throws PersistException;
}
