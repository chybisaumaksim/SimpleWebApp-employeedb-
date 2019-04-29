package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dao.PersistException;

public class EmployeeService implements Service {
//    private static Connection getConnection() throws PersistException {
//        Properties prop = new Properties();
//        InputStream is = null;
//        try {
//            is = EmployeeService.class.getClassLoader()
//                    .getResourceAsStream("application.properties");
//            prop.load(is);
//            Class.forName(prop.getProperty("driver"));
//            connection = DriverManager.getConnection(prop.getProperty("url"),
//                    prop.getProperty("login"), prop.getProperty("password"));
//        } catch (SQLException e) {
//            throw new PersistException("Error connecting to the database ", e);
//        } catch (FileNotFoundException e) {
//            throw new PersistException("File config.properties not found ", e);
//        } catch (IOException e) {
//            throw new PersistException("Error in fileInputStream", e);
//        } catch (ClassNotFoundException e) {
//            throw new PersistException("Class " + prop.getProperty("driver") + " not found", e);
//        } finally {
//            try {
//                if (is != null) {
//                    is.close();
//                }
//            } catch (IOException e) {
//                System.err.println("Error closing stream InputStream" + e.getMessage());
//            }
//        }
//        return connection;
//    }

    @Override
    public EmployeeDao getEmployeeDao() throws PersistException {
        return new EmployeeDao();
    }
}
