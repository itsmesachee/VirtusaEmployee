package com.rigor.virtusaemployee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.SealedObject;

import com.rigor.virtusaemployee.dao.model.Employee;

public class EmployeeDaoimpl implements EmployeeDao {

	private Employee employee;
	private Connection connection;
	private Statement statement;
	private final String url = "jdbc:mysql://localhost:3306/bd_test";

	public Employee getEmployee(int id) throws SQLException {
		try {
			String query = "Select * From virtusaEmployee where eid = " + id;
			connection = DriverManager.getConnection(url, "root", "root");
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			if (rs.first()) {
				employee = new Employee(rs.getInt("eid"), rs.getString("name"), rs.getString("nic"),
						rs.getString("salary"), rs.getString("currentPro"));
			}
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return employee;

	}

	public List<Employee> getAllEmployee() throws SQLException {
		List<Employee> empList = new ArrayList<Employee>();
		try {
			String query = "Select * From virtusaEmployee ";
			connection = DriverManager.getConnection(url, "root", "root");
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				employee = new Employee(rs.getInt("eid"), rs.getString("name"), rs.getString("nic"),
						rs.getString("salary"), rs.getString("currentPro"));
				empList.add(employee);
			}

		} finally {

			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return empList;
	}

	public void createEmployee(Employee emp) throws SQLException {

		try {
			String query = "Insert into virtusaemployee (eid, name, nic, salary, currentPro) Values(" + emp.getEid()
					+ ",'" + emp.getName() + "','" + emp.getNic() + "','" + emp.getSalary() + "','"
					+ emp.getCurrentproject() + "')";
			connection = DriverManager.getConnection(url, "root", "root");
			PreparedStatement prstatement = connection.prepareStatement(query);
			int val = prstatement.executeUpdate();
			System.err.println("created by " + val);
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

	public void deleteEmployee(Employee emp) throws SQLException {

		try {
			String query = "DELETE FROM `bd_test`.`virtusaemployee` WHERE `eid`='" + emp.getEid() + "';";
			connection = DriverManager.getConnection(url, "root", "root");
			PreparedStatement prstatement = connection.prepareStatement(query);
			int val = prstatement.executeUpdate();
			System.err.println("rows affected " + val);
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

	public Employee getEmployee(String name) throws SQLException {

		try {
			String query = "Select * from virtusaemployee where name = '" + name + "'";
			connection = DriverManager.getConnection(url, "root", "root");
			PreparedStatement prstatement = connection.prepareStatement(query);
			ResultSet rs = prstatement.executeQuery();

			while (rs.next()) {
				employee = new Employee(rs.getInt("eid"), rs.getString("name"), rs.getString("nic"),
						rs.getString("salary"), rs.getString("currentPro"));
			}
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return employee;
	}
	
	public void updateEmployee(Employee emp, String currentPro) throws SQLException {

		try {
			String query = "UPDATE virtusaemployee set currentPro = '"+ currentPro +"' where eid = '" + emp.getEid() + "'";
			connection = DriverManager.getConnection(url, "root", "root");
			PreparedStatement prstatement = connection.prepareStatement(query);
			int val = prstatement.executeUpdate();
			System.err.println("rows effected :"+val);
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

}
