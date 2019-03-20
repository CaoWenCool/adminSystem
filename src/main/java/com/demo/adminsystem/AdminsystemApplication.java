package com.demo.adminsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 应用启动主入口
 */
@SpringBootApplication
@EnableAsync
public class AdminsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminsystemApplication.class, args);
	}

}
