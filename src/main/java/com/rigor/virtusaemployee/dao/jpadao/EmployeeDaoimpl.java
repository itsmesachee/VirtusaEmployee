package com.rigor.virtusaemployee.dao.jpadao;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.rigor.virtusaemployee.dao.EmployeeDao;
import com.rigor.virtusaemployee.dao.model.Employee;

@Component
@Qualifier("dao")
public class EmployeeDaoimpl implements EmployeeDao {

	private EntityManager entityManager;
	private Employee emp;
	
	public static EntityManager build() {
		EntityManagerFactory mn = Persistence.createEntityManagerFactory("EmployeeManager");
		return mn.createEntityManager();
	}

	public Employee getEmployee(int id) throws SQLException {
		entityManager = build();
		entityManager.getTransaction().begin();
		Employee emp = entityManager.find(Employee.class, id);
		entityManager.getTransaction().commit();
		return emp;
	}

	public List<Employee> getAllEmployee() throws SQLException {
		entityManager = build();
		entityManager.getTransaction().begin();
		List<Employee> values = entityManager.createQuery("SELECT e FROM Employee e").getResultList();
		entityManager.getTransaction().commit();
		return values;
	}

	public void createEmployee(Employee emp) throws SQLException {
		entityManager = build();
		entityManager.getTransaction().begin();
		entityManager.persist(emp);
		entityManager.getTransaction().commit();
	}

	public void deleteEmployee(Employee emp) throws SQLException {
		entityManager = build();
		Employee empD = entityManager.find(Employee.class, emp.getEid());
		entityManager.getTransaction().begin();
		entityManager.remove(empD);
		entityManager.getTransaction().commit();

	}

	public Employee getEmployee(String name) throws SQLException {
		entityManager = build();
		entityManager.getTransaction().begin();
		List<Employee> values = entityManager.createQuery("SELECT e FROM Employee e").getResultList();
		Iterator<Employee> itr = values.iterator();
		while (itr.hasNext()) {
			Employee emp1 = itr.next();
			if(emp1.getName().equals(name)){
				emp = emp1;
			}
		}
		return emp;
	}

	public void updateEmployee(Employee emp, String currentPro) throws SQLException {
		entityManager = build();
		Employee empD = entityManager.find(Employee.class, emp.getEid());
		emp.setCurrentproject(currentPro);
		entityManager.getTransaction().begin();
		entityManager.remove(empD);
		entityManager.persist(emp);
		entityManager.getTransaction().commit();

	}

	@Override
	public String toString() {
		return "EmployeeDaoimpl [entityManager=" + entityManager + ", emp=" + emp + "]";
	}
	
	

}
