package com.java.maven.EmployeePayroll;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class EmployeePayrollIO {

	public static String PAYROLL_FNAME = "payroll.txt";
	/**
	 * Method to write list containing Emp Data to file
	 * @param List containing Emp Data
	 */
	public void writeData(List<EmployeePayrollData> list) {

		StringBuffer empBuffer = new StringBuffer();
		list.forEach(employee -> {
			String employeeDataStr = employee.pushData().concat("\n");
			empBuffer.append(employeeDataStr);
		});

		try {
			Files.write(Paths.get(PAYROLL_FNAME), empBuffer.toString().getBytes());
		}catch(IOException exception) {
			exception.printStackTrace();
		}
	}
	/**
	 * Method for counting entries in File
	 * @return NoOfEntries
	 */

	public int countEntries() {

		int entries = 0;

		try {
			entries = (int) Files.lines(new File(PAYROLL_FNAME).toPath()).count();
		}catch(IOException exception) {
			exception.printStackTrace();
		}
		return entries;
	}
	/**Method to write data from file to console
	 *
	 */
	public void printData() {
		try {
			Files.lines(new File(PAYROLL_FNAME).toPath()).forEach(System.out::println);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
