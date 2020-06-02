package com.azay.restfulexercise.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.azay.restfulexercise.exception.EmployeeNotFoundException;
import com.azay.restfulexercise.model.Employee;
import com.azay.restfulexercise.repository.EmployeeRepository;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This class implements Employee rest controller to take care of mapping
 * request data to the defined request handler method.
 * 
 * @author vadithya Narayana
 * @version 1.0
 */

@EnableSwagger2
@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * This returns all the available employees
	 * 
	 * @return list of employees in DB
	 */
	@GetMapping(path = "/employees")
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	/**
	 * This retrieve employee by specified employee ID number
	 * 
	 * @param id the id of the employee to retrieve
	 * @return employee object based on specified id
	 */
	@GetMapping("/employees/{id}")
	public Employee retrieveEmployeeByID(@PathVariable long id) {
		Optional<Employee> emp = employeeRepository.findById(id);
		if (!emp.isPresent())
			throw new EmployeeNotFoundException("id-" + id);

		return emp.get();
	}

	/**
	 * This create employee in DB with specified employee deatils
	 * 
	 * @param emp the employee object to be add to DB
	 * @return Response entity object with created location.
	 */
	@PostMapping("/employees")
	public ResponseEntity<Object> createEmployee(@RequestBody Employee emp) {

		Employee savedEmp = employeeRepository.save(emp);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedEmp.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}

	/**
	 * This delete the employee using specified employee Identity Number
	 * 
	 * @param id the id of employee to be delete from DB
	 */
	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable long id) {
		Optional<Employee> emp = employeeRepository.findById(id);
		if (!emp.isPresent())
			throw new EmployeeNotFoundException("id-" + id);

		employeeRepository.deleteById(id);
	}

	/**
	 * This update the details of specific employee using specified employee object
	 * and identity number.
	 * 
	 * @param emp the employee details to be updated in DB
	 * @param id  the id of employee to be updated in DB
	 * @return response entity with no content.
	 */
	@PutMapping("/employees/{id}")
	public ResponseEntity<Object> updateEmployee(@RequestBody Employee emp, @PathVariable long id) {

		Optional<Employee> empOptional = employeeRepository.findById(id);

		if (!empOptional.isPresent())
			return ResponseEntity.notFound().build();

		emp.setId(id);

		employeeRepository.save(emp);

		return ResponseEntity.noContent().build();
	}
}
