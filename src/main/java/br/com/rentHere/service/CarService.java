package br.com.rentHere.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.rentHere.model.Car;

public interface CarService {

	public Page<Car> getAllcars(Pageable pageable);

	public Optional<Car> getCarById(Long id);

	public Car insertCar(Car car);

	public Car updateCar(Car user);

	public void deleteCar(Long id);

	public Car carExists(Long id);
}
