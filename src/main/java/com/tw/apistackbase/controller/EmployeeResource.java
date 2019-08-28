package com.tw.apistackbase.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.tw.apistackbase.domain.Employee;
import com.tw.apistackbase.domain.Employees;

@RestController
@RequestMapping("/employees")
public class EmployeeResource {

	Employees employees = new Employees();
	
	@GetMapping(path = "")
	public ResponseEntity<List<Employee>> queryAll(){
		return ResponseEntity.ok(employees.getEmployees());
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Employee> queryEmployee(@PathVariable int id){
		for (Employee employee : employees.getEmployees()) {
			if (employee.getId() == id) {
				return ResponseEntity.ok(employee);
			}
		}
		return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(path = "/")
	public ResponseEntity<List<Employee>> queryEmployeesWithPageAndPageSize(@RequestParam Map<String, String> pageQuery){
		int startIndex = (Integer.valueOf(pageQuery.get("page")) -1) * Integer.valueOf(pageQuery.get("pageSize"));
		List<Employee> tatloEmployees = employees.getEmployees();
		List<Employee> returnEmployees = new ArrayList<>();
		if (tatloEmployees.size() >= startIndex && tatloEmployees.size() < startIndex + Integer.valueOf(pageQuery.get("pageSize"))) {
			returnEmployees = tatloEmployees.subList(startIndex,tatloEmployees.size());
		} else if(tatloEmployees.size() > startIndex + Integer.valueOf(pageQuery.get("pageSize"))){
			returnEmployees = tatloEmployees.subList(startIndex,startIndex +Integer.valueOf(pageQuery.get("pageSize")));
		}				
		return ResponseEntity.ok(returnEmployees);
	}
	
	@GetMapping(path = "/queryByGender")
	public ResponseEntity<List<Employee>> queryEmployeeByGender(@RequestParam String gender){
		List<Employee> resultEmployees = new ArrayList<>();
		for (Employee employee : employees.getEmployees()) {
			if (employee.getGender().equalsIgnoreCase(gender)) {
				resultEmployees.add(employee);
			}
		}
		return ResponseEntity.ok(resultEmployees);
	}
	
	@PostMapping(path = "")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee createEmployee(@RequestBody Employee employee){
		employees.setEmployee(employee);
		return employee;
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable int id,@RequestBody Employee employee){
		for (Employee beforeEmployee : employees.getEmployees()) {
			if (beforeEmployee.getId() == id) {
				employees.getEmployees().remove(beforeEmployee);
				employees.setEmployee(employee);
				return ResponseEntity.ok(employee);
			}
		}	
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable int id){
			for (Employee beforeEmployee : employees.getEmployees()) {
				if (beforeEmployee.getId() == id) {
					employees.getEmployees().remove(beforeEmployee);
					return ResponseEntity.ok(beforeEmployee);
				}	
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   
	}
}
