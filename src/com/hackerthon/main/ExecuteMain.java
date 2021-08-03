package com.hackerthon.main;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import com.hackerthon.common.UtilTRANSFORM;
import com.hackerthon.service.GetEmpService;

public class ExecuteMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		GetEmpService employeeService = new GetEmpService();
		try {
			UtilTRANSFORM.rEQUESTtRANSFORM();
			employeeService.EMPLOEESFROMXML();
			employeeService.eMPLOYEEtABLEcREATE();
			employeeService.eMPLOYEESaDD();
//			employeeService.eMPLOYEEGETBYID("EMP10004");
//			employeeService.EMPLOYEEDELETE("EMP10001");
			employeeService.eMPLOYEEdISPLAY();
		} catch (Exception e) {
		}

	}

}
