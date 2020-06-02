package com.azay.restfulexercise.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;

/**
 * This class implements a user model
 * 
 * @author vadithya narayana
 * @version 1.0
 */
@ApiModel(description = "All details about the Users")
@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;
	private String username;
	private String password;

	public User() {
		super();
	}

	public User(Long id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public String getUserName() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}