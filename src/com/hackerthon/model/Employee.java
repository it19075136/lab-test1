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
	
    // static variable single_instance of type Singleton
	private static Employee employee = null;
	
    // static method to create instance of Employee class
    public static Employee getInstance()
    {
        if (employee == null)
            employee = new Employee();
  
        return employee;
    }
    
   
    //get Employee ID
	public String getEmpID() {
		return idEmployee;
	}
	//set Employee ID
	public void setEmpID(String employeeID) {
		this.idEmployee = employeeID;
	}
	//get Full Name
	public String getFullName() {
		return nameFull;
	}
	//set Full Name
	public void setFullName(String fullName) {
		this.nameFull = fullName;
	}
	//get Address
	public String getAddress() {
		return address;
	}
	//set Address
	public void setAddress(String address) {
		this.address = address;
	}
	//get Faculty Name
	public String getFacultyName() {
		return facultyName;
	}
	//set Faculty Name
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	//get Department 
	public String getDepartment() {
		return department;
	}
	//set Department
	public void setDepartment(String department) {
		this.department = department;
	}
	//get getDesignation
	public String getDesignation() {
		return designation;
	}
	//set designation 
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	@Override
	public String toString() {
		return "Employee ID = " + idEmployee + "\n" + "FullName = " + nameFull + "\n" + "Address = " + address + "\n"
				+ "Faculty Name = " + facultyName + "\n" + "Department = " + department + "\n" + "Designation = "
				+ designation;
	}
}
