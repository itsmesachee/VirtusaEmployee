package com.rigor.virtusaemployee.front.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

		EmployeeService.createEmployee(eid, name, nic, salary, currentPro);
	}
	
	public static EmployeeModel getEmployee(int id) throws SystemErrorException, InvaliInputException {

		Employee emp = EmployeeService.getEmployee(id);
		return new EmployeeModel(emp.getName(), emp.getNic(), emp.getCurrentproject());
	}
	
	public static int getEmployee(String name) throws SystemErrorException, InvaliInputException {

		Employee emp = EmployeeService.getEmployee(name);
		return emp.getEid();
	}

	public static List<EmployeeModel> getAllEmployee() throws SystemErrorException {

		List<Employee> empList = EmployeeService.getEmployee();
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

		EmployeeService.deleteEmployee(id);
		
	}

	public static void updateEmployee(int id, String currentPro) throws SystemErrorException {
		
		EmployeeService.updateEmployee(id, currentPro);
	}
}
