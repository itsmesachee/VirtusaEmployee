package com.rigor.virtusaemployee.front.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.rigor.virtusaemployee.dao.model.Employee;
import com.rigor.virtusaemployee.front.model.EmployeeModel;
import com.rigor.virtusaemployee.service.EmployeeService;
import com.rigor.virtusaemployee.service.InvaliInputException;
import com.rigor.virtusaemployee.service.SystemErrorException;

public class ViewController {

	private ViewController() {
		throw new IllegalStateException();
	}

	
	public static void newEmployee(int eid, String name, String nic, String salary, String currentPro)
			throws SystemErrorException {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("EmpConfig.xml");
		EmployeeService employeeService = (EmployeeService) context.getBean("employeeService");
		employeeService.createEmployee(eid, name, nic, salary, currentPro);
	}
	
	public static EmployeeModel getEmployee(int id) throws SystemErrorException, InvaliInputException {

		ApplicationContext context = new ClassPathXmlApplicationContext("EmpConfig.xml");
		EmployeeService employeeService = (EmployeeService) context.getBean("employeeService");
		Employee emp = employeeService.getEmployee(id);
		return new EmployeeModel(emp.getName(), emp.getNic(), emp.getCurrentproject());
	}
	
	public static int getEmployee(String name) throws SystemErrorException, InvaliInputException {

		ApplicationContext context = new ClassPathXmlApplicationContext("EmpConfig.xml");
		EmployeeService employeeService = (EmployeeService) context.getBean("employeeService");
		Employee emp = employeeService.getEmployee(name);
		return emp.getEid();
	}

	public static List<EmployeeModel> getAllEmployee() throws SystemErrorException {

		ApplicationContext context = new ClassPathXmlApplicationContext("EmpConfig.xml");
		EmployeeService employeeService = (EmployeeService) context.getBean("employeeService");
		List<Employee> empList = employeeService.getEmployee();
		List<EmployeeModel> empViewList = new ArrayList<EmployeeModel>();
		Iterator<Employee> itr = empList.iterator();
		while (itr.hasNext()) {
			Employee emp = itr.next();
			EmployeeModel empv = new EmployeeModel(emp.getName(), emp.getNic(), emp.getCurrentproject());
			empViewList.add(empv);
		}
		return empViewList;
	}

	public static void deleteEmployee(int id) throws SystemErrorException, InvaliInputException {

		ApplicationContext context = new ClassPathXmlApplicationContext("EmpConfig.xml");
		EmployeeService employeeService = (EmployeeService) context.getBean("employeeService");
		employeeService.deleteEmployee(id);		
	}

	public static void updateEmployee(int id, String currentPro) throws SystemErrorException {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("EmpConfig.xml");
		EmployeeService employeeService = (EmployeeService) context.getBean("employeeService");
		employeeService.updateEmployee(id, currentPro);
	}
}
