package com.rigor.virtusaemployee.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.rigor.virtusaemployee.service.EmployeeService;

@Configuration
//@ComponentScan(basePackages = "com.rigor.virtusaemployee")
public class EmployeeConfiguration {
	
	@Bean(name = "dao")
	public EmployeeDao getEmployeeDao(){
		return new EmployeeDaoimpl();
	}
	
	@Bean(name = "employeeService")
	public EmployeeService getEmployeeService(){
		return new EmployeeService();
	}
	
}
