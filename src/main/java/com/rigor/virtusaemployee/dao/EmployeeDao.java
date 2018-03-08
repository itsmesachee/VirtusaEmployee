package com.rigor.virtusaemployee.dao;

import java.sql.SQLException;
import java.util.List;

import com.rigor.virtusaemployee.dao.model.Employee;

public interface EmployeeDao {

	public Employee getEmployee(int id) throws SQLException;
	public List<Employee> getAllEmployee() throws SQLException;
	public void createEmployee(Employee emp) throws SQLException;
	public void deleteEmployee(Employee emp) throws SQLException;
	public Employee getEmployee(String name) throws SQLException;
	public void updateEmployee(Employee emp, String currentPro) throws SQLException;


}
