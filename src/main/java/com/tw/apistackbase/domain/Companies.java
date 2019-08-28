package com.tw.apistackbase.domain;

import java.util.ArrayList;
import java.util.List;

public class Companies {

	private List<Company> companies = new ArrayList<Company>();

	public Companies() {
		companies.add(new Company(1,"aaf",new Employees().getEmployees()));
		companies.add(new Company(2,"zybank",new Employees().getEmployees()));
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompany(Company company) {
		this.companies.add(company);
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}
}
