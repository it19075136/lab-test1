package com.hackerthon.main;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import com.hackerthon.common.TransformUtil;
import com.hackerthon.service.EmployeeService;
import com.hackerthon.service.GetEmpService;

import sun.rmi.runtime.Log;

public class ExecuteMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EmployeeService employeeService = new EmployeeService();
		try {
			TransformUtil.rEQUESTtRANSFORM();
			employeeService.employeesFromXML();
			employeeService.createEmployeeTable();
			employeeService.addEmployee();
//			employeeService.eMPLOYEEGETBYID("EMP10004");
//			employeeService.EMPLOYEEDELETE("EMP10001");
			employeeService.displayEmployee();
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}

	}

}
