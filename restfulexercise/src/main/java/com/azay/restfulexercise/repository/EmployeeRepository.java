package com.azay.restfulexercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.azay.restfulexercise.model.Employee;

/**
 * 
 * @author vadithya narayana
 * @version 1.0
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
