package com.mastery.java.task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
public class AppConfiguration {

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

    @Bean
    public DataSource getDataSource() {
        Properties prop = new Properties();
        InputStream is = null;
        try {
            is = AppConfiguration.class.getClassLoader()
                    .getResourceAsStream("application.properties");
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing stream InputStream" + e.getMessage());
            }
        }
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(prop.getProperty("driver"));
        dataSource.setUrl(prop.getProperty("url"));
        dataSource.setUsername(prop.getProperty("login"));
        dataSource.setPassword(prop.getProperty("password"));
        return dataSource;
    }
}
