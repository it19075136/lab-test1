package com.hackerthon.main;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.hackerthon.common.TransformUtil;
import com.hackerthon.service.EmployeeService;


public class ExecuteMain {

	private static Logger logger = Logger.getLogger(EmployeeService.class.toString());

	/**
	 * @param args
	 * 
	 */
	public static void main(String[] args) {

		EmployeeService employeeService = new EmployeeService();
		try {
			TransformUtil.requestTransform();
			employeeService.employeesFromXML();
			employeeService.createEmployeeTable();
			employeeService.addEmployee();
			employeeService.getEmployeeById("EMP10004");
			employeeService.deleteEmployee("EMP10001");
			employeeService.displayEmployee();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}

	}

}
