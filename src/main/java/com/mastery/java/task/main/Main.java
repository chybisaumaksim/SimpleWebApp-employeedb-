//package com.mastery.java.task.main;
//
//import com.mastery.java.task.dao.Dao;
//import com.mastery.java.task.service.Service;
//import com.mastery.java.task.dao.PersistException;
//import com.mastery.java.task.dto.Employee;
//import com.mastery.java.task.service.EmployeeService;
//
//import java.util.List;
//
//
///**
// * @author Maksim Chybisau
// * @project Project
// */
//
//public class Main {
//    public static void main(String[] args) throws PersistException {
//
//        Dao employeeDao = null;
//        try {
//            Service employeeService = new EmployeeService();
//            employeeDao = employeeService.getEmployeeDao();
//            List<Employee> employees = employeeDao.getAll();
//            for (Employee empl : employees) {
//                System.out.println(empl.getEmployeeId() + " " + empl.getFirstName() + " " + empl.getLastName()
//                        + " " + empl.getDepartmentId() + " " + empl.getJobTitle() + " " + empl.getGender() + " " + empl.getDateOfBirth());
//            }
//        } catch (PersistException e) {
//            throw new PersistException("Ошибка Sql запроса в классе Main", e);
//        } finally {
//            try {
//                if (employeeDao != null) {
//                    employeeDao.close();
//                }
//            } catch (PersistException e) {
//                System.err.println("Ошибка закрытия employeeDao" + e.getMessage());
//            }
//        }
//    }
//}
