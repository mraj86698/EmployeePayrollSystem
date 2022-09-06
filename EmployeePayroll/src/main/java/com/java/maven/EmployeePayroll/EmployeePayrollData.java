package com.java.maven.EmployeePayroll;

public class EmployeePayrollData {
	public int id;
	public String name;
	public double salary;
	/**
	 * Parameterised Constructor
	 * @param id
	 * @param name
	 * @param salary
	 */
	public EmployeePayrollData(int id, String name, double salary) {
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	/**
	 * To Display the Employee Details
	 */
	public void printData() {
		System.out.println("------------------------------------------------------");
		System.out.println("Employee ID: "+id);
		System.out.println("Employee Name: "+name);
		System.out.println("Employee Salary: "+salary);
		System.out.println("------------------------------------------------------");
		System.out.println();
	}
	public String pushData() {
		return "id : " + id + ", Name : " + name + ", salary : " + salary;
	}
	/**
	 * It Returns the value into String Format
	 */
	@Override
	public String toString() {
		return "Created new Employee Data !";
	}
}
