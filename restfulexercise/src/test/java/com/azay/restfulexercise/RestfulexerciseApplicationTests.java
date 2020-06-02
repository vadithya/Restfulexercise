package com.azay.restfulexercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.azay.restfulexercise.controllers.EmployeeController;
import com.azay.restfulexercise.model.Employee;
import com.azay.restfulexercise.repository.EmployeeRepository;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = EmployeeController.class, useDefaultFilters = false)
public class RestfulexerciseApplicationTests {

	@Autowired
	MockMvc mockMvc;
	
	
	@MockBean
	private EmployeeRepository employeeRepository;
	
	@Test
	public void getEmployeesTest() {
		when(employeeRepository.findAll()).thenReturn(Stream
				.of(new Employee(5L, "employeTest1"), new Employee(6L, "employeTest2")).collect(Collectors.toList()));
		assertEquals(2, employeeRepository.findAll().size());
	}
	

}
