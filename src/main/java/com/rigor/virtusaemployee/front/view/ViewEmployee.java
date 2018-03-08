
package com.rigor.virtusaemployee.front.view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.LogManager;

import org.hibernate.jpa.boot.spi.Bootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rigor.virtusaemployee.front.controller.ViewController;
import com.rigor.virtusaemployee.front.model.EmployeeModel;
import com.rigor.virtusaemployee.service.InvaliInputException;
import com.rigor.virtusaemployee.service.SystemErrorException;

import ch.qos.logback.classic.Level;

public class ViewEmployee {

	private static final Logger LOGGER = LoggerFactory.getLogger(ViewEmployee.class);

	public static void main(String[] args) {
		
		LogManager.getLogManager().reset();
		List<EmployeeModel> viewList;
		Scanner input = new Scanner(System.in);
		while (true) {
			try {
				System.out.println();
				System.out.println("1. Get Employee by id");
				System.out.println("2. Get All Employees");
				System.out.println("3. Create Employee");
				System.out.println("4. Remove Employee");
				System.out.println("5. Find Employee id");
				System.out.println("6. Update Employee details:");
				System.out.println("7. Exit");
				int select = input.nextInt();
				LOGGER.debug("value selected :"+select);
				if(0<select && select>7){
					LOGGER.warn("Invalid Select value "+select);
				}
				if (select == 1) {
					System.out.println("employee id :");
					int id = input.nextInt();
					LOGGER.debug("employee id :" +id);
					System.out.println(ViewController.getEmployee(id).toString());
				}
				if (select == 2) {
					viewList = ViewController.getAllEmployee();
					Iterator<EmployeeModel> itr = viewList.iterator();
					while (itr.hasNext()) {
						System.out.println(itr.next());
					}
				}
				if (select == 3) {
					System.out.println("Enter employee details:");
					System.out.println("Employee id:");
					int eid = input.nextInt();
					System.out.println("Employee name:");
					String name = input.next();
					System.out.println("Employee nic:");
					String nic = input.next();
					System.out.println("Employee salary:");
					String salary = input.next();
					System.out.println("Employee currentPro:");
					String currentPro = input.next();
					LOGGER.debug("Employee id :"+eid+" name :"+name+" nic :"+nic+" salary :"+salary+" current project :"+currentPro);
					ViewController.newEmployee(eid, name, nic, salary, currentPro);
				}
				if (select == 4) {
					System.out.println("Enter Employee id to remove:");
					int eid = input.nextInt();
					LOGGER.debug("Employye id to remove :"+eid);
					ViewController.deleteEmployee(eid);
				}
				if (select == 5) {
					System.out.println("Enter Employee name:");
					String name = input.next();
					LOGGER.debug("Employee name :" +name);
					System.out.println("Employee id is :" + ViewController.getEmployee(name));

				}
				if (select == 6) {
					System.out.println("Enter Employee id:");
					int id = input.nextInt();
					System.out.println("project to update:");
					String currentPro = input.next();
					LOGGER.debug("Employee id :"+id+ " and project for update :"+currentPro);
					ViewController.updateEmployee(id, currentPro);
					System.out.println("Succesfully Updated");
				}
				if (select == 7) {
					LOGGER.debug("selected to terminate");
					break;
				}
			} catch (SystemErrorException e) {
				System.out.println("System Error");
				System.out.println("Please retry");
				System.exit(0);
			} catch (InvaliInputException e) {
				System.out.println("Invalid input");
				System.out.println("Please retry");
				System.exit(0);
			}
		}
		System.out.println("terminated");
		System.exit(0);
	}
}
