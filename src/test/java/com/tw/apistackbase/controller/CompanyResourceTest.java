package com.tw.apistackbase.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.apistackbase.domain.Companies;
import com.tw.apistackbase.domain.Company;
import com.tw.apistackbase.domain.Employees;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper ObjectMapper;

	@Test
	public void shouldReturnCompanyList() throws Exception {
		Companies companies = new Companies();
		String getString = ObjectMapper.writeValueAsString(companies.getCompanies());
		this.mockMvc.perform(get("/companies")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().json(getString));
	}

	@Test
	public void shouldReturnCompany0f1() throws Exception {
		Companies companies = new Companies();
		String getString = ObjectMapper.writeValueAsString(companies.getCompanies().get(0));
		this.mockMvc.perform(get("/companies/" + 1)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().json(getString));
	}

	@Test
	public void shouldReturnCompanyEmployees() throws Exception {
		Companies companies = new Companies();
		String getString = ObjectMapper.writeValueAsString(companies.getCompanies().get(0).getEmployees());
		this.mockMvc.perform(get("/companies/" + 1 + "/employees")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().json(getString));
	}

	@Test
	public void shouldReturnCompanyListqueryCompaniesWithPageAndPageSize() throws Exception {
		Companies companies = new Companies();
		String getString = ObjectMapper.writeValueAsString(companies.getCompanies());
		this.mockMvc.perform(get("/companies/?page=" + 1 + "&pageSize=" + 5)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().json(getString));
	}

	@Test
	@AfterAll
	public void shouldReturnCreateCompany() throws Exception {
		Company company = new Company(3, "asfas", new Employees().getEmployees());
		String postString = ObjectMapper.writeValueAsString(company);
		this.mockMvc
				.perform(
						MockMvcRequestBuilders.post("/companies")
						.contentType(MediaType.APPLICATION_JSON)
						.content(postString))
				        .andDo(print())
				        .andExpect(status().isCreated())
				        .andExpect(content().json(postString));
	}
	
}
