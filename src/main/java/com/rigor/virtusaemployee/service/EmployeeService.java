package com.rigor.virtusaemployee.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.rigor.virtusaemployee.dao.EmployeeConfiguration;
import com.rigor.virtusaemployee.dao.EmployeeDao;
import com.rigor.virtusaemployee.dao.EmployeeDaoimpl;
import com.rigor.virtusaemployee.dao.model.Employee;
import com.rigor.virtusaemployee.front.controller.ViewController;
import com.rigor.virtusaemployee.front.view.ViewEmployee;

public class EmployeeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
	private static final String xmlFile = "EmpConfig.xml";

	//@Autowired
	private EmployeeDao dao; 
	/*
	 * //xml configuration 
	 * public static EmployeeDao getEmployeeDao(){
	 * ApplicationContext appcon = new ClassPathXmlApplicationContext(xmlFile);
	 * dao = (EmployeeDao) appcon.getBean("dao"); return dao; }
	 */

	// javabase configuration
	public static EmployeeDao getEmployeeDao() {
		ApplicationContext appcon = new AnnotationConfigApplicationContext(EmployeeConfiguration.class);
		return (EmployeeDao) appcon.getBean("dao");
		
	}
	
	public List<Employee> getEmployee() throws SystemErrorException {

		dao = getEmployeeDao();
		try {
			return dao.getAllEmployee();
		} catch (SQLException e) {
			LOGGER.warn("SOLEXCEPTION", e);
			throw new SystemErrorException();
		}
	}

	public Employee getEmployee(int id) throws SystemErrorException, InvaliInputException {

		dao = getEmployeeDao();
		try {
			if (dao.getEmployee(id) != null) {
				return dao.getEmployee(id);
			} else {
				LOGGER.warn("Invalid Input :" + id);
				throw new InvaliInputException();
			}
		} catch (SQLException ex) {
			LOGGER.warn("SOLEXCEPTION", ex);
			throw new SystemErrorException();
		}
	}

	public void createEmployee(int eid, String name, String nic, String salary, String currentPro)
			throws SystemErrorException {

		dao = getEmployeeDao();
		try {
			dao.createEmployee(new Employee(eid, name, nic, salary, currentPro));
		} catch (SQLException e) {
			LOGGER.warn("SOLEXCEPTION", e);
			throw new SystemErrorException();
		}
	}

	public void deleteEmployee(int eid) throws SystemErrorException, InvaliInputException {

		dao = getEmployeeDao();
		try {
			dao.deleteEmployee(getEmployee(eid));
		} catch (SQLException e) {
			LOGGER.warn("SOLEXCEPTION", e);
			throw new SystemErrorException();
		}
	}

	public Employee getEmployee(String name) throws SystemErrorException, InvaliInputException {

		dao = getEmployeeDao();
		try {
			if (dao.getEmployee(name) != null) {
				return dao.getEmployee(name);
			} else {
				LOGGER.warn("Invalid Input :" + name);
				throw new InvaliInputException();
			}
		} catch (SQLException e) {
			LOGGER.warn("SOLEXCEPTION", e);
			throw new SystemErrorException();
		}
	}

	public void updateEmployee(int id, String currentPro) throws SystemErrorException {

		dao = getEmployeeDao();
		try {
			dao.updateEmployee(dao.getEmployee(id), currentPro);
		} catch (SQLException e) {
			LOGGER.warn("SOLEXCEPTION", e);
			throw new SystemErrorException();
		}
	}
}
