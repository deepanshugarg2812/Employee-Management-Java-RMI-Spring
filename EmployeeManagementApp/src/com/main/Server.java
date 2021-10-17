package com.main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.base.EmployeeFunctions;

public class Server{

	Server() throws RemoteException{
		super();
	}

	public static void main(String[] args) {
		try {
			ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
			EmployeeFunctions ef = ac.getBean("employeeManage",EmployeeManage.class);
			
			Registry registery = LocateRegistry.createRegistry(8080);
			registery.bind("manageapp",ef);
		}
		catch(Exception e) {
			System.out.println("Error occured");
		}
	}

}
