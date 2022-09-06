package com.java.maven.EmployeePayroll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayroll {

	public enum IOService{
		CONSOLE_IO,FILE_IO,DB_IO,REST_IO
	}

	private List<EmployeePayrollData> list;

	/**
	 * Constructor For Main Class
	 *
	 */
	public EmployeePayroll() {
		list = new ArrayList<EmployeePayrollData>();
	}
	/**
	 * Globally Declared for Scanner
	 */
	static Scanner sc = new Scanner(System.in);

	/**
	 * Read Employee Data from console
	 * Add data to Employee Data List
	 */
	private void readEmployeeData() {

		System.out.print("Enter Employee ID : ");
		int id = sc.nextInt();
		System.out.print("Enter Employee name : ");
		String name = sc.next();
		System.out.print("Enter Employee salary : ");
		double salary = sc.nextDouble();
		EmployeePayrollData employee = new EmployeePayrollData(id, name, salary);
		System.out.println(employee);
		list.add(employee);
		sc.close();
	}

	/**
	 * Write Employee Data to console
	 */
	private void writeEmployeeData() {
		System.out.println("Employee Data:");
		for (EmployeePayrollData employee : list) {
			employee.printData();
		}
	}

	// Main Method
	public static void main(String[] args) {
		EmployeePayroll emp = new EmployeePayroll();
		emp.readEmployeeData();
		emp.writeEmployeeData();
	}
}
