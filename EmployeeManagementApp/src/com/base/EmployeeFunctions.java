package com.base;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

public interface EmployeeFunctions extends Remote{
	
	//Method to add an employee
	public void addEmployee(Employee employee) throws RemoteException;
	
	//Method to delete an employee
	public void deleteEmployee(int id) throws RemoteException;
	
	//Method to get list of employee
	public List<Employee> getEmployeeDetail() throws RemoteException;
}
