package br.com.rentHere.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.rentHere.dto.UserDto;
import br.com.rentHere.dto.UserUpdateDto;

@SpringBootTest
@AutoConfigureMockMvc
public class RentControllerTest {
	private static final String APPLICATION_JSON = "application/json";
	private static final String URL = "http://localhost:8080/v1/rent/user/";
	private static final String URL_USER_ID = "http://localhost:8080/v1/rent/user/1";
	@Autowired
	private MockMvc mock;
	@Autowired
    private ObjectMapper objectMapper;

	@Test
	public void test01InsertUserWithSucess() throws JsonProcessingException, Exception {
		mock.perform(post(URL)
				.contentType(APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(newUser())))
		.andExpect(status().isCreated());
	}

	@Test
	public void test02InsertUserWithError() throws JsonProcessingException, Exception {
		mock.perform(post(URL)
				.contentType(APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(new UserDto("", "test", ""))))
		.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void test03UpdateUserWithSucess() throws JsonProcessingException, Exception {
		mock.perform(put(URL_USER_ID)
				.contentType(APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updateUser())))
		.andExpect(status().isNoContent());
	}

	@Test
	public void test04UpdateUserWithError() throws JsonProcessingException, Exception {
		mock.perform(put(URL)
				.contentType(APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(new UserUpdateDto(null,"test user", "test@gmail.com", "00000000"))))
		.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void test05DeleteUserWithSucess() throws JsonProcessingException, Exception {
		mock.perform(delete(URL_USER_ID)
				.contentType(APPLICATION_JSON))
		.andExpect(status().is4xxClientError());
	}
	
	private UserDto newUser() {
		return new UserDto("test user", "test@gmail.com", "00000000000");
	}
	
	private UserUpdateDto updateUser() {		
		return new UserUpdateDto(1L,"test user", "test@gmail.com", "000000000");
	}
}
