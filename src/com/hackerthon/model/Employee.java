package com.hackerthon.model;
/**
 * 
 * @author 2021S2_REG_WE_18
 * 
 * Employee Class 
 *
 */
public class Employee {

	private String idEmployee;
	private String nameFull;
	private String address;
	private String facultyName;
	private String department;
	private String designation;
	
    //get Employee ID
	public String getEmpID() {
		return idEmployee;
	}
	//set Employee ID
	public void empID(String employeeID) {
		this.idEmployee = employeeID;
	}
	//get Full Name
	public String getFullName() {
		return nameFull;
	}
	//set Full Name
	public void fullName(String fullName) {
		this.nameFull = fullName;
	}
	//get Address
	public String getAddress() {
		return address;
	}
	//set Address
	public void address(String address) {
		this.address = address;
	}
	//get Faculty Name
	public String getFacultyName() {
		return facultyName;
	}
	//set Faculty Name
	public void facultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	//get Department 
	public String getDepartment() {
		return department;
	}
	//set Department
	public void department(String department) {
		this.department = department;
	}
	//get getDesignation
	public String getDesignation() {
		return designation;
	}
	//set designation 
	public void designation(String designation) {
		this.designation = designation;
	}
	
	@Override
	public String toString() {
		return "Employee ID = " + idEmployee + "\n" + "FullName = " + nameFull + "\n" + "Address = " + address + "\n"
				+ "Faculty Name = " + facultyName + "\n" + "Department = " + department + "\n" + "Designation = "
				+ designation;
	}
}
