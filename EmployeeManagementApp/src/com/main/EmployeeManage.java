package com.main;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.base.Employee;
import com.base.EmployeeFunctions;

public class EmployeeManage extends UnicastRemoteObject implements EmployeeFunctions{
	private static final long serialVersionUID = 1L;
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	protected EmployeeManage() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addEmployee(Employee employee) throws RemoteException {
//		System.out.println("This is the addEmployee method called");
		String sql = "INSERT INTO employees (name,doj,salary,phone,address) VALUES (?,?,?,?,?);";
		this.jdbcTemplate.update(sql,new Object[] {employee.getName(),employee.getDateOfJoining(),employee.getSalary(),employee.getPhone(),employee.getAddress()});
	}

	@Override
	public void deleteEmployee(int id) throws RemoteException {
		//System.out.println("This is the deleteEmployee method called");
		String sql = "DELETE FROM employees where id=?";
		this.jdbcTemplate.update(sql,new Object[] {id});
	}

	@Override
	public List<Employee> getEmployeeDetail() throws RemoteException {
		// TODO Auto-generated method stub
		//System.out.println("This is the getEmployeeDetails method called");
		String sql = "SELECT * FROM employees";
		return this.jdbcTemplate.query(sql,new EmployeeMapper());
	}
	
	private static final class EmployeeMapper implements RowMapper<Employee>{

		@Override
		public Employee mapRow(ResultSet arg0, int arg1) throws SQLException {
			Employee employee = new Employee(
					arg0.getString(2),
					arg0.getDate(3),
					arg0.getInt(4),
					arg0.getString(5),
					arg0.getString(6)
					);
			return employee;
		}
		
	}
}
