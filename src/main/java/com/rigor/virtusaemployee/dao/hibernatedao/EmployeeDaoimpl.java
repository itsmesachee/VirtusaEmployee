package com.rigor.virtusaemployee.dao.hibernatedao;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.rigor.virtusaemployee.dao.EmployeeDao;
import com.rigor.virtusaemployee.dao.model.Employee;

public class EmployeeDaoimpl implements EmployeeDao {

	private SessionFactory sessionfactory;
	private Employee employee;
	
	public static SessionFactory buildSession() {
		Configuration configuration = new Configuration();
		configuration.configure().addAnnotatedClass(Employee.class);
		return configuration.buildSessionFactory();
	}

	public Employee getEmployee(int id) throws SQLException {
		sessionfactory = buildSession();
		Session session = sessionfactory.openSession();
		Transaction transaction = session.beginTransaction();
		List<Employee> values = session.createCriteria(Employee.class).list();
		transaction.commit();
		session.close();
		
		Iterator<Employee> itr = values.iterator();
		while(itr.hasNext()){
			Employee emp = itr.next();
			if(emp.getEid() == id){
				employee = emp;
			}
		}
		return employee;
	}

	public List<Employee> getAllEmployee() throws SQLException {
		
		sessionfactory = buildSession();
		Session session = sessionfactory.openSession();
		Transaction transaction = session.beginTransaction();
		List values = session.createCriteria(Employee.class).list();
		transaction.commit();
		session.close();
		return values;
	}

	public void createEmployee(Employee emp) throws SQLException {

		sessionfactory = buildSession();
		Session session = sessionfactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(emp);
		transaction.commit();
		session.close();
	}

	public void deleteEmployee(Employee emp) throws SQLException {
		sessionfactory = buildSession();
		Session session = sessionfactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(emp);
		transaction.commit();
		session.close();
	}

	public Employee getEmployee(String name) throws SQLException {
		sessionfactory = buildSession();
		Session session = sessionfactory.openSession();
		Transaction transaction = session.beginTransaction();
		List<Employee> values = session.createCriteria(Employee.class).list();
		transaction.commit();
		session.close();
		
		Iterator<Employee> itr = values.iterator();
		while(itr.hasNext()){
			Employee emp = itr.next();
			if(emp.getName().equals(name)){
				employee = emp;
				break;
			}else{
				employee= null;
			}
		}
		return employee;
	}

	public void updateEmployee(Employee emp, String currentPro) throws SQLException {
		sessionfactory = buildSession();
		Session session = sessionfactory.openSession();
		Transaction transaction = session.beginTransaction();
		emp.setCurrentproject(currentPro);
		session.update(emp);
		transaction.commit();
		session.close();
		
		

	}
}
