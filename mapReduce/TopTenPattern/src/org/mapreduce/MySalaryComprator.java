package org.mapreduce;

import java.util.Comparator;

public class MySalaryComprator implements Comparator<Employee> {

	@Override
	public int compare(Employee emp1, Employee emp2) {
	
		return (int) (emp2.getSal() - emp1.getSal());
	}

}
