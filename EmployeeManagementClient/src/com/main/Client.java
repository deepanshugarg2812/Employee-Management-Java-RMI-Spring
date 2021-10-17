package com.main;

import java.rmi.Naming;
import java.sql.Date;

import com.base.Employee;
import com.base.EmployeeFunctions;

public class Client {

	public static void main(String[] args) {
		try {
			EmployeeFunctions ef = (EmployeeFunctions) Naming.lookup("rmi://localhost:8080/manageapp");
			ef.addEmployee(new Employee("sample",Date.valueOf("2021-10-10"),1000000,"12345","sample address"));
			System.out.println(ef.getEmployeeDetail());
			ef.deleteEmployee(1);
			System.out.println(ef.getEmployeeDetail());
		}
		catch(Exception e) {
			System.out.println("Error occured " + e.getMessage());
		}

	}

}
