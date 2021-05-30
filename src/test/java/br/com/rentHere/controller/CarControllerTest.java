package br.com.rentHere.controller;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.rentHere.model.Car;
import br.com.rentHere.serviceImpl.CarServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTest {

	@Mock
	private CarServiceImpl carService;
	
	@Mock
	private RestTemplate restTemplate;

	@Test
	public void test01InsertCarWithSucess() throws JsonProcessingException, Exception {
		Car car = newCar();
		when(carService.insertCar(car)).thenReturn(car);
		
		Car carSaved = carService.insertCar(car);
		assertEquals(car.getId(), carSaved.getId());
	}

	@Test
	public void test02InsertUserWithError() throws JsonProcessingException, Exception {
		Car car = new Car();
		when(carService.insertCar(car)).thenReturn(car);
		assertThatNullPointerException();
	}

	@Test
	public void test03UpdateUserWithSucess() throws JsonProcessingException, Exception {
		Car carUpdate = updateCar();
		when(carService.updateCar(carUpdate)).thenReturn(carUpdate);
		
		Car carSaved = carService.updateCar(carUpdate);
		assertEquals(carUpdate.getId(), carSaved.getId());
	}

	@Test
	public void test04UpdateUserWitherror() throws JsonProcessingException, Exception {
		Car carUpdate = new Car(null, "abc1234", "corsa", 60.0, null);
		when(carService.updateCar(carUpdate)).thenReturn(carUpdate);
		assertThatNullPointerException();
	}
	
	@Test
	public void test04DeleteUserWithSucess() throws JsonProcessingException, Exception {
		Optional<Car> car = Optional.of(newCar());
		when(carService.getCarById(1L)).thenReturn(car);
		
		carService.deleteCar(car.get().getId());
	    verify(carService, times(1)).deleteCar(1L);

	}
	
	private Car newCar() {
		return new Car(1L, "abc1234", "corsa", 60.0, 1L);
	}
	
	private Car updateCar() {
		Car carUpdate = newCar();
		carUpdate.setModel("Mob fiat");
		return carUpdate;
	}
}
