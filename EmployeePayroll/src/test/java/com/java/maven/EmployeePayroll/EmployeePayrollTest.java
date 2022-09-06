package com.java.maven.EmployeePayroll;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.java.maven.EmployeePayroll.EmployeePayroll.IOCommand;


public class EmployeePayrollTest {

	EmployeePayroll employee;

	@Before
	public void init() {
		EmployeePayrollData[] arrayOfEmp = {
				new EmployeePayrollData(1,"Jeff Bezos",100000.0),
				new EmployeePayrollData(2, "Bill Gates",200000.0),
				new EmployeePayrollData(3, "Mark Zuckerberg",300000.0)
		};

		employee = new EmployeePayroll();
		employee.setList(Arrays.asList(arrayOfEmp));
		employee.writeEmployeeData(IOCommand.FILE_IO);
	}

	@Test
	public void givenThreeEmployeesWhenWrittenToFileShouldMatchEmployeeEntries() {
		assertEquals(3, employee.countEntries(IOCommand.FILE_IO));
	}
}