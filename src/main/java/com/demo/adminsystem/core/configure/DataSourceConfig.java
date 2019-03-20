package com.demo.adminsystem.core.configure;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * @author: admin
 * @create: 2019/3/20
 * @update: 11:01
 * @version: V1.0
 * @detail:
 **/
@Configuration
public class DataSourceConfig {


    @Bean(name = "dataSource")
    public DataSource getDataSource(Environment environment){
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(environment.getProperty("spring.datasource.url"));
        ds.setUsername(environment.getProperty("spring.datasource.username"));
        ds.setPassword(environment.getProperty("spring.datasource.password"));
        ds.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));

        return  ds;
    }
}
