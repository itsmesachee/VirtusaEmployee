package com.rigor.virtusaemployee.front.model;

public class EmployeeModel {

	@Override
	public String toString() {
		return "Employee name=" + name + ", nic=" + nic + ", current Project=" + currentPro;
	}
	private String name;
	private String nic;
	private String currentPro;
	
	public String getCurrentPro() {
		return currentPro;
	}
	public void setCurrentPro(String currentPro) {
		this.currentPro = currentPro;
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
	public EmployeeModel(String name, String nic, String currentPro) {
		super();
		this.name = name;
		this.nic = nic;
		this.currentPro = currentPro;
	}
	
	
}
