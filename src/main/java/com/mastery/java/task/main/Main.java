package com.mastery.java.task.main;

import com.mastery.java.task.dao.EmployeeServiceInterface;
import com.mastery.java.task.dao.PersistException;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.service.EmployeeDao;
import com.mastery.java.task.service.EmployeeService;


/**
 * @author Maksim Chybisau
 * @project Project
 */

public class Main {
    public static void main(String[] args) throws PersistException {

        EmployeeDao employeeDao = null;
        try {
            EmployeeServiceInterface employeeService = new EmployeeService();
            employeeDao = employeeService.getEmployeeDao();
//
//            List<Employee> employees = employeeDao.getAll();
//            for (Employee empl : employees) {
//                System.out.println(empl.getEmployeeId() + " " + empl.getFirstName() + " " + empl.getLastName()
//                        + " " + empl.getDepartmentId() + " " + empl.getJobTitle()+ " " + empl.getGender()+ " " + empl.getDateOfBirth());
//            }
//
            Employee employee = new Employee();
            employee.setEmployeeId(2L);
            employee.setFirstName("Victor");
            employee.setLastName("Popov");
            employee.setDepartmentId(3L);
            employee.setJobTitle("cheef");
            employee.setGender(Gender.MALE);
            employee.setDateOfBirth("19890101");
            employeeDao.update(employee);
//
            Employee employee2 = new Employee();
            employee2.setFirstName("Sergei");
            employee2.setLastName("Sergeev");
            employee2.setDepartmentId(3L);
            employee2.setJobTitle("kook");
            employee2.setGender(Gender.MALE);
            employee2.setDateOfBirth("19670607");
            employeeDao.create(employee2);
//
            Employee employee3 = new Employee();
            employee3.setEmployeeId(3L);
            employeeDao.delete(employee3);
//
            Employee employee4 = employeeDao.getById(17);
            System.out.println(employee4);
        } catch (PersistException e) {
            throw new PersistException("Ошибка Sql запроса в классе Main", e);
        } finally {
            try {
                if (employeeDao != null) {
                    employeeDao.close();
                }
            } catch (PersistException e) {
                System.err.println("Ошибка закрытия employeeDao" + e.getMessage());
            }
        }
    }
}
