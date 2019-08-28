package com.tw.apistackbase.domain;

import java.util.ArrayList;
import java.util.List;

public class Employees {
  
	private List<Employee> employees = new ArrayList<Employee>();
	
	public Employees() {
		employees.add(new Employee(1,"as",2,"male",205));
		employees.add(new Employee(2,"ass",2,"dasdas",205));
		employees.add(new Employee(3,"asa",2,"male",205));
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployee(Employee employee) {
		this.employees.add(employee);
	}
	
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
}
