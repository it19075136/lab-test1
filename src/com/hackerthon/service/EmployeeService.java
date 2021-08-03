package com.hackerthon.service;

import org.xml.sax.SAXException;
import java.sql.Connection;
import java.util.logging.Logger;
import java.sql.DriverManager;
import javax.xml.parsers.ParserConfigurationException;
import java.sql.PreparedStatement;
import javax.xml.xpath.XPathExpressionException;
import com.hackerthon.common.UtilTRANSFORM;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.sql.Statement;
import com.hackerthon.common.UtilQ;
import java.io.IOException;
import com.hackerthon.model.Employee;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import com.hackerthon.common.CommonConstants;
import com.hackerthon.common.UtilC;

public class EmployeeService extends UtilC {

	private final ArrayList<Employee> employeeList = new ArrayList<Employee>();

	private static Connection c;

	private static Statement s;
	
	private static Logger logger = Logger.getLogger(EmployeeService.class.toString());
	
	private Properties properties;

	private PreparedStatement ps;
	
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

	public void EMPLOEESFROMXML() {

		try {
			int s = UtilTRANSFORM.XMLXPATHS().size();
			for (int i = 0; i < s; i++) {
				Map<String, String> l = UtilTRANSFORM.XMLXPATHS().get(i);
				Employee emplo = new Employee();
				EMPLOYEE.empID(l.get("XpathEmployeeIDKey"));
				EMPLOYEE.fULLnAME(l.get("XpathEmployeeNameKey"));
				EMPLOYEE.address(l.get("XpathEmployeeAddressKey"));
				EMPLOYEE.fACULTYNAME(l.get("XpathFacultyNameKey"));
				EMPLOYEE.department(l.get("XpathDepartmentKey"));
				EMPLOYEE.designation(l.get("XpathDesignationKey"));
				employeeList.add(EMPLOYEE);
				System.out.println(EMPLOYEE.toString() + "\n");
			}
		} catch (Exception e) {
		}
	}

	public void eMPLOYEEtABLEcREATE() {
		try {
			s = c.createStatement();
			s.executeUpdate(UtilQ.Q("q2"));
			s.executeUpdate(UtilQ.Q("q1"));
		} catch (Exception e) {
		}
	}

	public void eMPLOYEESaDD() {
		try {
			ps = c.prepareStatement(UtilQ.Q("q3"));
			c.setAutoCommit(false);
			for(int i = 0; i < employeeList.size(); i++){
				Employee e = employeeList.get(i);
				ps.setString(1, e.getEmpID());
				ps.setString(2, e.getFullName());
				ps.setString(3, e.getAddress());
				ps.setString(4, e.getFacultyName());
				ps.setString(5, e.getDepartment());
				ps.setString(6, e.getDesignation());
				ps.addBatch();
			}
			ps.executeBatch();
			c.commit();
		} catch (Exception e) {
		}
	}

	public void eMPLOYEEGETBYID(String eid) {

		Employee e = new Employee();
		try {
			ps = c.prepareStatement(UtilQ.Q("q4"));
			ps.setString(1, eid);
			ResultSet R = ps.executeQuery();
			while (R.next()) {
				e.empID(R.getString(1));
				e.fULLnAME(R.getString(2));
				e.address(R.getString(3));
				e.fACULTYNAME(R.getString(4));
				e.department(R.getString(5));
				e.designation(R.getString(6));
			}
			ArrayList<Employee> l = new ArrayList<Employee>();
			l.add(e);
			employeeOutput(l);
		} catch (Exception ex) {
		}
	}

	public void EMPLOYEEDELETE(String eid) {

		try {
			ps = c.prepareStatement(UtilQ.Q("q6"));
			ps.setString(1, eid);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void eMPLOYEEdISPLAY() {

		ArrayList<Employee> l = new ArrayList<Employee>();
		try {
			ps = c.prepareStatement(UtilQ.Q("q5"));
			ResultSet r = ps.executeQuery();
			while (r.next()) {
				Employee e = new Employee();
				e.empID(r.getString(1));
				e.fULLnAME(r.getString(2));
				e.address(r.getString(3));
				e.fACULTYNAME(r.getString(4));
				e.department(r.getString(5));
				e.designation(r.getString(6));
				l.add(e);
			}
		} catch (Exception e) {
		}
		employeeOutput(l);
	}
	
	public void employeeOutput(ArrayList<Employee> empList){
		
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
