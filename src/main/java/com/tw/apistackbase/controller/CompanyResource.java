package com.tw.apistackbase.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tw.apistackbase.domain.Companies;
import com.tw.apistackbase.domain.Company;
import com.tw.apistackbase.domain.Employee;

@RestController
@RequestMapping("/companies")
public class CompanyResource {
	
	Companies companies = new Companies();
	
	@GetMapping(path = "")
	public ResponseEntity<List<Company>> queryAll(){
		return ResponseEntity.ok(companies.getCompanies());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Company> queryCompany(@PathVariable int id){
		for (Company company : companies.getCompanies()) {
			if (company.getId() == id) {
				return ResponseEntity.ok(company);
			}
		}
		return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(path = "/{id}/employees")
	public ResponseEntity<List<Employee>> queryCompanyEmployees(@PathVariable int id){
		for (Company company : companies.getCompanies()) {
			if (company.getId() == id) {
				return ResponseEntity.ok(company.getEmployees());
			}
		}
		return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(path = "/")
	public ResponseEntity<List<Company>> queryCompaniesWithPageAndPageSize(@RequestParam Map<String, String> pageQuery){
		int startIndex = (Integer.valueOf(pageQuery.get("page")) -1) * Integer.valueOf(pageQuery.get("pageSize"));
		List<Company> tatloCompanies = companies.getCompanies();
		List<Company> returnCompanies = new ArrayList<>();
		if (tatloCompanies.size() >= startIndex && tatloCompanies.size() < startIndex + Integer.valueOf(pageQuery.get("pageSize"))) {
			returnCompanies = tatloCompanies.subList(startIndex,tatloCompanies.size());
		} else if(tatloCompanies.size() > startIndex + Integer.valueOf(pageQuery.get("pageSize"))){
			returnCompanies = tatloCompanies.subList(startIndex,startIndex +Integer.valueOf(pageQuery.get("pageSize")));
		}				
		return ResponseEntity.ok(returnCompanies);
	}
	
	@PostMapping(path = "")
	@ResponseStatus(HttpStatus.CREATED)
	public Company createCompany(@RequestBody Company company){
		System.out.println(company.getEmployees().size());
		company.setEmployeesNumber(company.getEmployees().size());
		companies.setCompany(company);			
		return company;
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<Company> updateCompany(@PathVariable int id,@RequestBody Company company){
		for (Company beforeCompany : companies.getCompanies()) {
			if (beforeCompany.getId() == id) {
				companies.getCompanies().remove(beforeCompany);
				companies.setCompany(company);
				return ResponseEntity.ok(company);
			}
		}	
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Company> deleteCompany(@PathVariable int id){
			for (Company beforeCompany : companies.getCompanies()) {
				if (beforeCompany.getId() == id) {
					companies.getCompanies().remove(beforeCompany);
					return ResponseEntity.ok(beforeCompany);
				}	
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   
	}
}
