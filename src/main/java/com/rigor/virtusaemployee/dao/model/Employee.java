package com.rigor.virtusaemployee.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "virtusaemployee")
public class Employee {

	@Id
	@Column(name = "eid")
	private int eid;

	@Column(name = "name")
	private String name;

	@Column(name = "nic")
	private String nic;

	@Column(name = "salary")
	private String salary;

	@Column(name = "currentPro")
	private String currentPro;

	public int getEid() {
		return eid;
	}

	public Employee() {

	}

	public Employee(int eid, String name, String nic, String salary, String currentproject) {
		super();
		this.eid = eid;
		this.name = name;
		this.nic = nic;
		this.salary = salary;
		this.currentPro = currentproject;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getCurrentproject() {
		return currentPro;
	}

	public void setCurrentproject(String currentproject) {
		this.currentPro = currentproject;
	}

	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", name=" + name + ", nic=" + nic + ", salary=" + salary + ", currentproject="
				+ currentPro + "]";
	}

}
