package com.mastery.java.task.rest;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dao.PersistException;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet("/main")
@WebListener
public class EmployeeController extends HttpServlet implements HttpSessionListener {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (PersistException e) {
            System.err.println("Request processing error doGet " + e.getMessage());
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (PersistException e) {
            System.err.println("Request processing error doPost " + e.getMessage());
        }
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException, PersistException {
        req.setCharacterEncoding("UTF-8");
        String action = checkAction(req);
        HttpSession session = req.getSession();
        EmployeeDao employeeDao = (EmployeeDao) session.getAttribute("employeeDao");
        if (action.equals("getAll")) {
            getAllEmployees(req, resp, employeeDao);
        } else if (action.equals("Create")) {
            createEmployee(req, resp, employeeDao);
        } else if (action.equals("Update")) {
            updateEmployee(req, resp, employeeDao);
        } else if (action.equals("Delete")) {
            deleteEmployee(req, resp, employeeDao);
        } else if (action.equals("getById")) {
            getByIdEmployee(req, resp, employeeDao);
        }
    }

    private void getByIdEmployee(HttpServletRequest req, HttpServletResponse resp, EmployeeDao employeeDao) throws PersistException {
        try {
            String id = req.getParameter("id");
            if (id != null) {
                req.setAttribute("employee", employeeDao.getById(Long.parseLong(id)));
            }
            getServletContext().getRequestDispatcher("/EmployeeGetById.jsp").forward(req, resp);
        } catch (IOException | PersistException | ServletException e) {
            throw new PersistException(e.getMessage() + "Error retrieving all employee records ");
        }
    }

    private void deleteEmployee(HttpServletRequest req, HttpServletResponse resp, EmployeeDao employeeDao) throws ServletException, IOException, PersistException {
        try {
            String id = req.getParameter("id");
            req.setAttribute("employee", employeeDao.getAll());
            if (id != null) {
                Employee employee = new Employee();
                employee.setEmployeeId(Long.parseLong(id));
                employeeDao.delete(employee);
                req.setAttribute("employee", employeeDao.getAll());
                req.setAttribute("messageDelete", "Employee deleted successfully");
            }
            getServletContext().getRequestDispatcher("/EmployeeDelete.jsp").forward(req, resp);
        } catch (IOException | PersistException | ServletException e) {
            throw new PersistException(e.getMessage() + "Error employee delete");
        }
    }

    private void updateEmployee(HttpServletRequest req, HttpServletResponse resp, EmployeeDao employeeDao) throws PersistException, IOException, ServletException {
        try {
            String id = req.getParameter("id");
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String departmentId = req.getParameter("departmentId");
            String jobTitle = req.getParameter("jobTitle");
            String gender = req.getParameter("gender");
            String dateOfBirth = req.getParameter("dateOfBirth");
            req.setAttribute("employee", employeeDao.getById(Long.parseLong(id)));
            if (firstName != null && lastName != null && departmentId != null && jobTitle != null && gender != null && dateOfBirth != null) {
                Employee employee = new Employee();
                employee.setEmployeeId(Long.parseLong(id));
                employee.setFirstName(firstName);
                employee.setLastName(lastName);
                employee.setDepartmentId(Long.parseLong(departmentId));
                employee.setJobTitle(jobTitle);
                employee.setGender(Gender.valueOf(gender));
                if (validationBirthDate(dateOfBirth)) {
                    req.setAttribute("messageForBirthDate", "Wrong format of birthday");
                    employee.setDateOfBirth("");
                } else {
                    employee.setDateOfBirth(dateOfBirth);
                    employeeDao.update(employee);
                    req.setAttribute("messageSuccess", "Employee record updated successfully");
                }
                req.setAttribute("employee", employee);
            }
            getServletContext().getRequestDispatcher("/EmployeeUpdate.jsp").forward(req, resp);
        } catch (IOException | PersistException |
                ServletException e) {
            throw new PersistException(e.getMessage() + " Error updating employee record ");
        }

    }

    private void createEmployee(HttpServletRequest req, HttpServletResponse resp, EmployeeDao studentDao) throws IOException, PersistException, ServletException {
        try {
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String departmentId = req.getParameter("departmentId");
            String jobTitle = req.getParameter("jobTitle");
            String gender = req.getParameter("gender");
            String dateOfBirth = req.getParameter("dateOfBirth");
            if (firstName != null && lastName != null && departmentId != null && jobTitle != null && gender != null && dateOfBirth != null) {
                Employee employee = new Employee();
                employee.setFirstName(firstName);
                employee.setLastName(lastName);
                employee.setDepartmentId(Long.parseLong(departmentId));
                employee.setJobTitle(jobTitle);
                employee.setGender(Gender.valueOf(gender));
                employee.setDateOfBirth(dateOfBirth);
                req.setAttribute("employee", employee);
                studentDao.create(employee);
                req.setAttribute("messageSuccess", "Employee Record created successfully");
            }
            getServletContext().getRequestDispatcher("/EmployeeCreate.jsp").forward(req, resp);
        } catch (IOException | PersistException | ServletException e) {
            throw new PersistException(e.getMessage() + "Error creating employee record");
        }
    }

    private void getAllEmployees(HttpServletRequest req, HttpServletResponse resp, EmployeeDao employeeDao) throws PersistException {
        try {
            req.setAttribute("employee", employeeDao.getAll());
            getServletContext().getRequestDispatcher("/EmployeeGetAll.jsp").forward(req, resp);
        } catch (IOException | PersistException | ServletException e) {
            throw new PersistException(e.getMessage() + "Error retrieving all employee records ");
        }
    }

    private String checkAction(HttpServletRequest req) {
        if (req.getParameter("getAll") != null) {
            return "getAll";
        }
        if (req.getParameter("Update") != null) {
            return "Update";
        }
        if (req.getParameter("Create") != null) {
            return "Create";
        }
        if (req.getParameter("Delete") != null) {
            return "Delete";
        }
        if (req.getParameter("getById") != null) {
            return "getById";
        }
        return null;
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        try {
            se.getSession().setAttribute("employeeDao", new EmployeeService().getEmployeeDao());
            System.out.println(" (session) Initial :ID = "
                    + se.getSession().getId());
        } catch (PersistException e) {
            System.err.println("Session creation error" + e.getMessage());
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        EmployeeDao employeeDao = (EmployeeDao) session.getAttribute("employeeDao");
        try {
            employeeDao.close();
            System.out.println(" (session) Destroyed: ID= "
                    + session.getId());
        } catch (PersistException e) {
            System.err.println("Session closing error" + e.getMessage());
        }
    }

    private boolean validationBirthDate(String birthDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        try {
            df.parse(birthDate);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

}
