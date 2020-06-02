package com.azay.restfulexercise;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.azay.restfulexercise.model.Employee;
import com.azay.restfulexercise.model.JwtRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class implements secured spring boot rest api integration testing
 * 
 * @author vadithya narayana
 * @version 1.0
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = RestfulexerciseApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestfulexerciseApplicationITest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private ObjectMapper mapper;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}

	/**
	 * This perform testing create a new employee with mock authentication
	 * 
	 * @throws Exception The exception throw if an error occurs when creating new
	 *                   employees
	 */
	@WithMockUser("/azay-e1")
	@Test
	public void testcreateEmployee() throws Exception {

		Employee employee = new Employee(5L, "employeTest1");
		String jsonRequest = mapper.writeValueAsString(employee);
		System.out.println("jsonRequest of create employee is " + jsonRequest);
		MvcResult result = mockMvc
				.perform(post("/employees").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();
		System.out.println("jsonResult status code " + result.getResponse().getStatus());
		assertEquals(201, result.getResponse().getStatus());
	}

	/**
	 * This perform testing to get all employee with mock authentication
	 * 
	 * @throws Exception The Exception throw if an error occurs when retrieving
	 *                   employees
	 */
	@WithMockUser("/azay-e1")
	@Test
	public void testGetEmployee() throws Exception {

		MvcResult result = mockMvc.perform(get("/employees").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());

	}

	/**
	 * This perform testing to authenticate user and retrive employees
	 * 
	 * @throws Exception The Exception throw if an error occurs when retrieving
	 *                   employees
	 */

	@Test
	public void testCreateAuthenticationToken() throws Exception {

		JwtRequest jwtRequest = new JwtRequest("test1", "password");
		String jsonRequest = mapper.writeValueAsString(jwtRequest);
		System.out.println("jsonRequest of Authenticate employee is " + jsonRequest);
		MvcResult result = mockMvc
				.perform(post("/authenticate").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String response = result.getResponse().getContentAsString();
		System.out.println("response token after Authenticate is " + response);
		JSONObject obj = new JSONObject(response);
		String token = obj.getString("token");
		MvcResult result2 = mockMvc.perform(MockMvcRequestBuilders.get("/employees")
				.header("Authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result2.getResponse().getStatus());

	}

}
