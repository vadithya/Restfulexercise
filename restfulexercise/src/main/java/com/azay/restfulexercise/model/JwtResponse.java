package com.azay.restfulexercise.model;

import java.io.Serializable;
/**
 * This class implements a JWT token that can be used around.
 * @author vadithya narayana
 *@version 1.0
 */
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}
}