package com.rigor.virtusaemployee.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rigor.virtusaemployee.dao.EmployeeDao;
import com.rigor.virtusaemployee.dao.model.Employee;
import com.rigor.virtusaemployee.front.controller.ViewController;
import com.rigor.virtusaemployee.front.view.ViewEmployee;

public class EmployeeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
	private static final String xmlFile = "EmpConfig.xml";
	
	private EmployeeService() {
		throw new IllegalStateException();
	}

	public static List<Employee> getEmployee() throws SystemErrorException {

		ApplicationContext appcon = new ClassPathXmlApplicationContext(xmlFile);
		EmployeeDao dao = (EmployeeDao) appcon.getBean("dao");

		try {
			return dao.getAllEmployee();
		} catch (SQLException e) {
			LOGGER.warn("SOLEXCEPTION", e);
			throw new SystemErrorException();
		}
	}

	public static Employee getEmployee(int id) throws SystemErrorException, InvaliInputException {

		ApplicationContext appcon = new ClassPathXmlApplicationContext(xmlFile);
		EmployeeDao dao = (EmployeeDao) appcon.getBean("dao");

		try {
			if (dao.getEmployee(id) != null) {
				return dao.getEmployee(id);
			}
			else{
				LOGGER.warn("Invalid Input :"+id);
				throw new InvaliInputException();
			}
		} catch(SQLException ex) {
			LOGGER.warn("SOLEXCEPTION", ex);
			throw new SystemErrorException();
		}
	}

	public static void createEmployee(int eid, String name, String nic, String salary, String currentPro)
			throws SystemErrorException {

		ApplicationContext appcon = new ClassPathXmlApplicationContext(xmlFile);
		EmployeeDao dao = (EmployeeDao) appcon.getBean("dao");

		try {
			dao.createEmployee(new Employee(eid, name, nic, salary, currentPro));
		} catch (SQLException e) {
			LOGGER.warn("SOLEXCEPTION", e);
			throw new SystemErrorException();
			}
	}

	public static void deleteEmployee(int eid) throws SystemErrorException, InvaliInputException {

		ApplicationContext appcon = new ClassPathXmlApplicationContext(xmlFile);
		EmployeeDao dao = (EmployeeDao) appcon.getBean("dao");

		try {
			dao.deleteEmployee(getEmployee(eid));
		} catch (SQLException e) {
			LOGGER.warn("SOLEXCEPTION", e);
			throw new SystemErrorException();
		}
	}

	public static Employee getEmployee(String name) throws SystemErrorException, InvaliInputException {

		ApplicationContext appcon = new ClassPathXmlApplicationContext(xmlFile);
		EmployeeDao dao = (EmployeeDao) appcon.getBean("dao");

		try {
			if (dao.getEmployee(name) != null) {
				return dao.getEmployee(name);
			}
			else{
				LOGGER.warn("Invalid Input :"+name);
				throw new InvaliInputException();
			}
		} catch (SQLException e) {
			LOGGER.warn("SOLEXCEPTION", e);
			throw new SystemErrorException();
		}
	}

	public static void updateEmployee(int id, String currentPro) throws SystemErrorException {
		
	ApplicationContext appcon = new ClassPathXmlApplicationContext(xmlFile);
	EmployeeDao dao = (EmployeeDao)appcon.getBean("dao");
	
	try {
		dao.updateEmployee(dao.getEmployee(id), currentPro);
	} catch (SQLException e) {
		LOGGER.warn("SOLEXCEPTION", e);
		throw new SystemErrorException();
	}
	}
}
