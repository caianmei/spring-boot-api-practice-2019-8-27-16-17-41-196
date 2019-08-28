package com.tw.apistackbase.domain;

import java.util.ArrayList;
import java.util.List;

public class Company {

	private int id;
	private String companyName;
	private int employeesNumber;
	List<Employee> employees = new ArrayList<Employee>();

	public Company() {

	}

	public Company(int id, String companyName, List<Employee> employees) {
		this.id = id;
		this.companyName = companyName;
		this.employees = employees;
		this.employeesNumber = employees.size();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getEmployeesNumber() {
		return employeesNumber;
	}

	public void setEmployeesNumber(int employeesNumber) {
		this.employeesNumber = employeesNumber;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public void setEmployee(Employee employee) {
		this.employees.add(employee);
	}
}
