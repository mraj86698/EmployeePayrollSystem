package com.java.maven.EmployeePayroll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayroll {

	public enum IOCommand{
		CONSOLE_IO,FILE_IO,DB_IO,REST_IO
	}

	private List<EmployeePayrollData> list;

	public void setList(List<EmployeePayrollData> list) {
		this.list = list;
	}
	/**
	 * Constructor For Main Class
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
	public void writeEmployeeData(IOCommand ioType) {
		if(ioType.equals(ioType.CONSOLE_IO)) {
			System.out.println("Writing Employee Payroll Data to Console.");
			for (EmployeePayrollData employee:list) {
				employee.printData();
			}
		}else if (ioType.equals(ioType.FILE_IO)){
			new EmployeePayrollIO().writeData(list);
			System.out.println("Write in File");
		}
	}

	public int countEntries(IOCommand ioType) {
		if(ioType.equals(IOCommand.FILE_IO))
			return new EmployeePayrollIO().countEntries();
		return 0;
	}

	// Main Method
	public static void main(String[] args) {
		EmployeePayroll emp = new EmployeePayroll();
		emp.readEmployeeData();
		emp.writeEmployeeData(IOCommand.CONSOLE_IO);
		emp.writeEmployeeData(IOCommand.FILE_IO);
	}

}
