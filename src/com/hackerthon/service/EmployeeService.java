package com.hackerthon.service;

import org.xml.sax.SAXException;
import java.sql.Connection;
import java.util.logging.Logger;
import java.sql.DriverManager;
import javax.xml.parsers.ParserConfigurationException;
import java.sql.PreparedStatement;
import javax.xml.xpath.XPathExpressionException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.sql.Statement;


import java.io.IOException;
import com.hackerthon.model.Employee;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import com.hackerthon.common.CommonConstants;
import com.hackerthon.common.UtilC;
import com.hackerthon.common.UtilTRANSFORM;

public class EmployeeService extends CommonUtil {


	private final ArrayList<Employee> employeeList = new ArrayList<Employee>();

	private static Connection c;

	private static Statement statement;
	
	private static Logger logger = Logger.getLogger(EmployeeService.class.toString());
	
	private Properties properties;

	private PreparedStatement preparedStatement;
	
	private static final String emp;

	public EmployeeService() {
		
			try {
				Class.forName(properties.getProperty(CommonConstants.DRIVER_NAME));
				c = DriverManager.getConnection(properties.getProperty(CommonConstants.URL), properties.getProperty(CommonConstants.USERNAME),
						properties.getProperty(CommonConstants.PASSWORD));
			} catch (ClassNotFoundException e) {
				logger.log(Level.SEVERE ,e.getMessage());
			}
			 catch (SQLException e) {
				logger.log(Level.SEVERE ,e.getMessage());
			}
	}

	public void employeesFromXML() {

		try {
			int s = TransformUtil.XMLXPATHS().size();
			for (int i = 0; i < s; i++) {
				Map<String, String> empList= UtilTRANSFORM.XMLXPATHS().get(i);
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
		}
	}

	public void createEmployeeTable() {
		try {
			statement = c.createStatement();
			statement.executeUpdate(QueryUtil.Q(CommonConstants.QUERY_ID_DROP_TABLE));
			statement.executeUpdate(QueryUtil.Q(CommonConstants.QUERY_ID_CREATE_TABLE));
		} catch (Exception e) {
		}
	}

	public void addEmployee() {
		try {
			ps = c.prepareStatement(QueryUtil.Q("q3"));
			c.setAutoCommit(false);
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
			ps.executeBatch();
			c.commit();
		} catch (Exception e) {
		}
	}

	public void getEmployeeById(String eid) {

		Employee e = new Employee();
		try {
			ps = c.prepareStatement(QueryUtil.Q("q4"));
			ps.setString(1, eid);
			ResultSet R = ps.executeQuery();
			while (R.next()) {
				e.empID(R.getString(1));
				e.fullName(R.getString(2));
				e.address(R.getString(3));
				e.facultyName(R.getString(4));
				e.department(R.getString(5));
				e.designation(R.getString(6));
			}
			ArrayList<Employee> l = new ArrayList<Employee>();
			l.add(e);
			outputEmployee(l);
		} catch (Exception ex) {
		}
	}

	public void DeleteEmployee(String eid) {

		try {
			ps = c.prepareStatement(QueryUtil.Q("q6"));
			ps.setString(1, eid);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void displayEmployee() {

		ArrayList<Employee> l = new ArrayList<Employee>();
		try {
			ps = c.prepareStatement(QueryUtil.Q("q5"));
			ResultSet r = ps.executeQuery();
			while (r.next()) {
				Employee e = new Employee();
				e.empID(r.getString(1));
				e.fullName(r.getString(2));
				e.address(r.getString(3));
				e.facultyName(r.getString(4));
				e.department(r.getString(5));
				e.designation(r.getString(6));
				l.add(e);
			}
		} catch (Exception e) {
		}
		outputEmployee(l);
	}
	
	public void outputEmployee(ArrayList<Employee> l){

		
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
