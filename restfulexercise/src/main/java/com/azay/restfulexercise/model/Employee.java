package com.azay.restfulexercise.model;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
/**
 * This class implement a employee model
 * @author vadithya narayana
 * @version 1.0
 */
@ApiModel(description = "All details about the employee")
@Entity
public class Employee {
	@Id
	private Long id;
	private String name;
	//private Date doj;
	public Employee() {
		super();
	}
	public Employee(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + "]";
	}

}
