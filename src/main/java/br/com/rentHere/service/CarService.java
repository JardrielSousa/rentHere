package br.com.rentHere.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.rentHere.exception.ResourceNotFoundException;
import br.com.rentHere.model.Car;
import br.com.rentHere.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CarService {
	
	private CarRepository carRepository;

	public CarService(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	public Page<Car> getAllcars(Pageable pageable){
		return carRepository.findAll(pageable);
	}
	
	public Optional<Car> getCarById(Long id){
		Car car = carExists(id);
		return Optional.of(car) ;
	}
	
	public Car insertCar(Car car) {
		return carRepository.save(car);
	}
	public Car updateCar(Car user) {
		return insertCar(user);
	}
	@Transactional
	public void deleteCar(Long id) {
		carRepository.deleteById(id);
	}
	
	private Car carExists(Long id) {
		log.info("verifing if exists");
		Optional<Car> car = carRepository.findById(id);
		if(car.isEmpty()) {
			throw new ResourceNotFoundException("not found for id: "+id);
		}
		return car.get();
	}
}
