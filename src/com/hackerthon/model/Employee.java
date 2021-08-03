package com.hackerthon.model;

public class Employee {

	public String employeeId;
	public String nAMEfULL;
	public String aDDRESS;
	public String fACULTYnAME;
	public String dEPARTMENT;
	public String dESIGNATION;
	private static Employee employee = null;
	
    // static method to create instance of Employee class
    public static Employee getInstance()
    {
        if (employee == null)
            employee = new Employee();
  
        return employee;
    }
    
	public String EMPLOYEEiDgET() {
		return employeeId;
	}
	public void eMPLOYEEiD(String employeeID) {
		employeeId = employeeID;
	}
	public String fULLnAMEgET() {
		return nAMEfULL;
	}
	public void fULLnAME(String fullName) {
		nAMEfULL = fullName;
	}
	public String aDDRESSgET() {
		return aDDRESS;
	}
	public void aDDRESS(String address) {
		aDDRESS = address;
	}
	public String fACULTYnAMEgET() {
		return fACULTYnAME;
	}
	public void fACULTYNAME(String facultyName) {
		fACULTYnAME = facultyName;
	}
	public String dEPARTMENTgET() {
		return dEPARTMENT;
	}
	public void dEPARTMENT(String department) {
		dEPARTMENT = department;
	}
	public String dESIGNATIONgET() {
		return dESIGNATION;
	}
	public void dESIGNATION(String designation) {
		dESIGNATION = designation;
	}
	@Override
	public String toString() {
		
		return "Employee ID = " + employeeId + "\n" + "FullName = " + nAMEfULL + "\n" + "Address = " + aDDRESS + "\n"
				+ "Faculty Name = " + fACULTYnAME + "\n" + "Department = " + dEPARTMENT + "\n" + "Designation = "
				+ dESIGNATION;
	}
}
