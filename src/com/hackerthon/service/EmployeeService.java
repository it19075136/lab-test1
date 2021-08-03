package com.hackerthon.service;

import java.sql.Connection;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import com.hackerthon.common.CommonConstants;
import com.hackerthon.common.CommonUtil;
import com.hackerthon.common.QueryUtil;
import com.hackerthon.common.TransformUtil;
import com.hackerthon.model.Employee;

public class EmployeeService extends CommonUtil {


	private final ArrayList<Employee> employeeList = new ArrayList<Employee>();

	private static Connection connection;

	private static Statement statement;
	
	private static Logger logger = Logger.getLogger(EmployeeService.class.toString());
	
	private Properties properties;

	private PreparedStatement preparedStatement;
	
	/**
	 * EmployeeService constructor
	 * 
	 * @throws ClassNotFoundException
	 * 				-Thrown when an application tries to load in a class through itsstring name using: 
	 * 				•The forName method in class Class. 
	 *				•The findSystemClass method in class ClassLoader . 
	 *				•The loadClass method in class ClassLoader.
     *@throws SQLException
     *				-An exception that provides information on a database accesserror or other errors.
	 */

	public EmployeeService() {
		
			try {
				Class.forName(properties.getProperty(CommonConstants.DRIVER_NAME));
				connection = DriverManager.getConnection(properties.getProperty(CommonConstants.URL), properties.getProperty(CommonConstants.USERNAME),
						properties.getProperty(CommonConstants.PASSWORD));
			} catch (ClassNotFoundException e) {
				logger.log(Level.SEVERE ,e.getMessage());
			}
			 catch (SQLException e) {
				logger.log(Level.SEVERE ,e.getMessage());
			}
	}
	
	/**
	 * employeeFromCML
	 * 
	 *@throws SQLException
     *				-An exception that provides information on a database accesserror or other errors.
	 */
	public void employeesFromXML() {

		try {
			int s = TransformUtil.XMLXPATHS().size();
			for (int i = 0; i < s; i++) {
				Map<String, String> empList= TransformUtil.XMLXPATHS().get(i);
				Employee employee = Employee.getInstance();
				employee.setEmpID(empList.get(CommonConstants.XPATH_EMP_ID));
				employee.setFullName(empList.get(CommonConstants.XPATH_EMP_NAME));
				employee.setAddress(empList.get(CommonConstants.XPATH_EMP_ADDRESS));
				employee.setFacultyName(empList.get(CommonConstants.XPATH_EMP_FACULTY));
				employee.setDepartment(empList.get(CommonConstants.XPATH_EMP_DEPARTMENT));
				employee.setDesignation(empList.get(CommonConstants.XPATH_EMP_DESIGNATION));
				employeeList.add(employee);
				logger.info(employee.toString() + "\n");
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}
	
	/**
	 * Create Employee Table
	 * 
	 *@throws SQLException
     *				-An exception that provides information on a database accesserror or other errors.
	 */
	public void createEmployeeTable() {
		try {
			statement = connection.createStatement();
			statement.executeUpdate(QueryUtil.Q(CommonConstants.QUERY_ID_DROP_TABLE));
			statement.executeUpdate(QueryUtil.Q(CommonConstants.QUERY_ID_CREATE_TABLE));

		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}
	
	/**
	 * addEmplyee 
	*@throws SQLException
     *				-An exception that provides information on a database accesserror or other errors.
	 */
	public void addEmployee() {
		try {
			preparedStatement = connection.prepareStatement(QueryUtil.Q(CommonConstants.QUERY_ID_INSERT_EMPLOYEES));
			connection.setAutoCommit(false);
			for(int i = 0; i < employeeList.size(); i++){
				Employee e = employeeList.get(i);
				preparedStatement.setString(1, e.getEmpID());
				preparedStatement.setString(2, e.getFullName());
				preparedStatement.setString(3, e.getAddress());
				preparedStatement.setString(4, e.getFacultyName());
				preparedStatement.setString(5, e.getDepartment());
				preparedStatement.setString(6, e.getDesignation());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			connection.commit();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		
	}
	
	/**
	 * getEmployeeById
	 * @param eid
	 * 
	 *@throws SQLException
     *				-An exception that provides information on a database accesserror or other errors.
	 */

	public void getEmployeeById(String eid) {
		Employee employee = Employee.getInstance();
			try {
				preparedStatement = connection.prepareStatement(QueryUtil.Q(CommonConstants.QUERY_ID_GET_EMPLOYEE_BYID));
				ResultSet R = preparedStatement.executeQuery();
				preparedStatement.setString(1, eid);
				while (R.next()) {
					employee.setEmpID(R.getString(1));
					employee.setFullName(R.getString(2));
					employee.setAddress(R.getString(3));
					employee.setFacultyName(R.getString(4));
					employee.setDepartment(R.getString(5));
					employee.setDesignation(R.getString(6));
				}
			} catch (SQLException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
			 catch (Exception e) {
					logger.log(Level.SEVERE, e.getMessage());
				}

			ArrayList<Employee> empList = new ArrayList<Employee>();
			empList.add(employee);
			outputEmployee(empList);

	}
	
	/**
	 * deleteEmployee 
	 * @param eid
	 *@throws SQLException
     *				-An exception that provides information on a database accesserror or other errors.
	 * 
	 */

	public void deleteEmployee(String eid) {

		try {
			preparedStatement = connection.prepareStatement(QueryUtil.Q(CommonConstants.DELETE_EMPLOYEE));
			preparedStatement.setString(1, eid);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}
	
	/**
	 * display Employees
	 *@throws SQLException
     *				-An exception that provides information on a database accesserror or other errors.
	 */

	public void displayEmployee() {
		
		ArrayList<Employee> empList = new ArrayList<Employee>();

			try {
				preparedStatement = connection.prepareStatement(QueryUtil.Q(CommonConstants.QUERY_ID_GET_ALL_EMPLOYEES));
				ResultSet r;
				r = preparedStatement.executeQuery();
				while (r.next()) {
					Employee employee = Employee.getInstance();
					employee.setEmpID(r.getString(1));
					employee.setFullName(r.getString(2));
					employee.setAddress(r.getString(3));
					employee.setFacultyName(r.getString(4));
					employee.setDepartment(r.getString(5));
					employee.setDesignation(r.getString(6));
					empList.add(employee);
				}
			} catch (SQLException e) {
				logger.log(Level.SEVERE, e.getMessage());
			} catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
			outputEmployee(empList);
	}
	
	/**
	 * Employee Output Display
	 * @param l
	 */
	public void outputEmployee(ArrayList<Employee> empList){

		
		System.out.println("Employee ID" + "\t\t" + "Full Name" + "\t\t" + "Address" + "\t\t" + "Faculty Name" + "\t\t"
				+ "Department" + "\t\t" + "Designation" + "\n");
		System.out
				.println("================================================================================================================");
		for(int emp = 0; emp < empList.size(); emp++){
			Employee e = empList.get(emp);
			System.out.println(e.getEmpID() + "\t" + e.getFullName() + "\t\t"
					+ e.getAddress() + "\t" + e.getFacultyName() + "\t" + e.getDepartment() + "\t"
					+ e.getDesignation() + "\n");
			System.out
			.println("----------------------------------------------------------------------------------------------------------------");
		}
		
	}
	
}
