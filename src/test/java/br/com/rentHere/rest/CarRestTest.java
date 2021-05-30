package br.com.rentHere.rest;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.rentHere.controller.CarController;
import br.com.rentHere.model.Car;
import br.com.rentHere.serviceImpl.CarServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
public class CarRestTest {

	private static final String ID = "/{id}";

	private final String BASE_URL = "http://localhost:8080/v1/rent/car";

	private MockMvc mockMvc;

	@Mock
	private CarServiceImpl carService;

	@InjectMocks
	private CarController carController;

	@BeforeEach
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(carController)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.setViewResolvers(new ViewResolver() {
					@Override
					public org.springframework.web.servlet.View resolveViewName(String viewName, Locale locale)
							throws Exception {
						return new MappingJackson2JsonView();
					}
				}).build();
	}

	@Test
	@DisplayName(value = "This test do get all cars")
	public void test_getAllCar_success() throws Exception {
		Page<Car> carsPage = new PageImpl<Car>(setCarsList());
		Pageable pageable = PageRequest.of(0, 20);
		when(carService.getAllcars(pageable)).thenReturn(carsPage);
		mockMvc.perform(get(BASE_URL)).andExpect(status().isOk());
		verify(carService, times(1)).getAllcars(pageable);
		verifyNoMoreInteractions(carService);
	}

	@Test
	@DisplayName(value = "This test insert car")
	public void test_postCar_success() throws Exception {
		Car car = new Car(null, "aaaaa", "classic", 50.00, 1L);
		when(carService.insertCar(car)).thenReturn(car);
		mockMvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON).content(asJsonString(car)))
				.andExpect(status().isCreated());
		verify(carService, times(1)).insertCar(car);
		verifyNoMoreInteractions(carService);
	}

	@Test
	@DisplayName(value = "This test update car with ID One")
	public void test_putCar_success() throws Exception {
		Car car = new Car(1L, "aaabbb", "gol", 50.00, 1L);
		when(carService.getCarById(car.getId())).thenReturn(Optional.of(car));
		when(carService.updateCar(car)).thenReturn(car);
		mockMvc.perform(put(BASE_URL.concat(ID), car.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(car))).andExpect(status().isNoContent());
		verify(carService, times(1)).getCarById(car.getId());
		verify(carService, times(1)).updateCar(car);
		verifyNoMoreInteractions(carService);
	}

	@DisplayName(value = "This test delete car with ID One")
	public void test_deleteCar_success() throws Exception {
		Car car = carIdOne();
		when(carService.getCarById(car.getId())).thenReturn(Optional.of(car));
		doNothing().when(carService).deleteCar(car.getId());
		mockMvc.perform(delete(BASE_URL.concat(ID), car.getId())).andExpect(status().isNoContent());
		verify(carService, times(1)).getCarById(car.getId());
		verify(carService, times(1)).deleteCar(car.getId());
		verifyNoMoreInteractions(carService);
	}

	private List<Car> setCarsList() {
		return Arrays.asList(carIdOne(), new Car(2L, "bbbbb", "gol", 70.00, 1L));
	}

	private Car carIdOne() {
		return new Car(1L, "aaaaa", "classic", 50.00, 1L);
	}

	private String asJsonString(Car user) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(user);
	}

}
