package br.com.rentHere.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.rentHere.model.dto.UserDto;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	private static final String APPLICATION_JSON = "application/json";
	private static final String URL = "http://localhost:8080/v1/rent/user";
	@Autowired
	private MockMvc mock;
	@Autowired
    private ObjectMapper objectMapper;

	@Test
	public void test01() throws JsonProcessingException, Exception {
		mock.perform(post(URL)
				.contentType(APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(newUser())))
		.andExpect(status().isCreated());
	}

	private UserDto newUser() {
		return new UserDto( "testa", "aaa", "9855555");
	}
}
