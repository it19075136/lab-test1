package com.hackerthon.service;

import org.xml.sax.SAXException;
import java.sql.Connection;
import java.util.logging.Logger;
import java.sql.DriverManager;
import javax.xml.parsers.ParserConfigurationException;
import java.sql.PreparedStatement;
import javax.xml.xpath.XPathExpressionException;
import com.hackerthon.common.TransformUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.sql.Statement;

import com.hackerthon.common.CommonConstants;
import com.hackerthon.common.CommonUtil;
import com.hackerthon.common.QueryUtil;
import java.io.IOException;
import com.hackerthon.model.Employee;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;


public class EmployeeService extends CommonUtil {


	private final ArrayList<Employee> employeeList = new ArrayList<Employee>();

	private static Connection c;

	private static Statement s;
	
	private static Logger logger = Logger.getLogger(EmployeeService.class.toString());
	
	private Properties properties;

	private PreparedStatement ps;

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
			int s = TransformUtil.XMLXPATHS().size();
			for (int i = 0; i < s; i++) {

				Map<String, String> l = TransformUtil.XMLXPATHS().get(i);
				Employee emplo = new Employee();
				emplo.empID(l.get("XpathEmployeeIDKey"));
				emplo.fullName(l.get("XpathEmployeeNameKey"));
				emplo.address(l.get("XpathEmployeeAddressKey"));
				emplo.facultyName(l.get("XpathFacultyNameKey"));
				emplo.department(l.get("XpathDepartmentKey"));
				emplo.designation(l.get("XpathDesignationKey"));
				employeeList.add(emplo);
				System.out.println(emplo.toString() + "\n");
			}
		} catch (Exception e) {
		}
	}

	public void eMPLOYEEtABLEcREATE() {
		try {
			s = c.createStatement();
			s.executeUpdate(QueryUtil.Q("q2"));
			s.executeUpdate(QueryUtil.Q("q1"));
		} catch (Exception e) {
		}
	}

	public void eMPLOYEESaDD() {
		try {
			ps = c.prepareStatement(QueryUtil.Q("q3"));
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
			eMPLOYEEoUTPUT(l);
		} catch (Exception ex) {
		}
	}

	public void EMPLOYEEDELETE(String eid) {

		try {
			ps = c.prepareStatement(QueryUtil.Q("q6"));
			ps.setString(1, eid);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void eMPLOYEEdISPLAY() {

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
		eMPLOYEEoUTPUT(l);
	}
	
	public void eMPLOYEEoUTPUT(ArrayList<Employee> l){
		
		System.out.println("Employee ID" + "\t\t" + "Full Name" + "\t\t" + "Address" + "\t\t" + "Faculty Name" + "\t\t"
				+ "Department" + "\t\t" + "Designation" + "\n");
		System.out
				.println("================================================================================================================");
		for(int i = 0; i < l.size(); i++){
			Employee e = l.get(i);
			System.out.println(e.getEmpID() + "\t" + e.getFullName() + "\t\t"
					+ e.getAddress() + "\t" + e.getFacultyName() + "\t" + e.getDepartment() + "\t"
					+ e.getDesignation() + "\n");
			System.out
			.println("----------------------------------------------------------------------------------------------------------------");
		}
		
	}
	
}
