package com.hackerthon.main;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import com.hackerthon.common.UtilTRANSFORM;
import com.hackerthon.service.EmployeeService;
import com.hackerthon.service.GetEmpService;

public class ExecuteMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EmployeeService employeeService = new EmployeeService();
		try {
			UtilTRANSFORM.rEQUESTtRANSFORM();
			employeeService.employeesFromXML();
			employeeService.createEmployeeTable();
			employeeService.addEmployee();
//			employeeService.eMPLOYEEGETBYID("EMP10004");
//			employeeService.EMPLOYEEDELETE("EMP10001");
			employeeService.displayEmployee();
		} catch (Exception e) {
		}

	}

}
