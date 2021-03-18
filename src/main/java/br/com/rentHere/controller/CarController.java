package br.com.rentHere.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.rentHere.dto.CarDto;
import br.com.rentHere.dto.CarUpdateDto;
import br.com.rentHere.model.Car;
import br.com.rentHere.service.CarService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("v1/rent/car")
public class CarController {

	private CarService carService;

	public CarController(CarService carService) {
		this.carService = carService;
	}
	
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Page<Car> getAllCar(Pageable pageable) {
		return carService.getAllcars(pageable);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Car getCarById(@PathVariable("id") Long id, Pageable pageable) {
		Optional<Car> car = carService.getCarById(id);
		return car.get();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Car saveCar(@Valid @RequestBody CarDto carDto) {
		log.info("analising data ... ");
		Car user = new Car().convertDtoToEntity(carDto);
		log.info("Car was saved!!!");
		return carService.insertCar(user);
	}

	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity<?> updateCar(@Valid @PathVariable("id") Long id, @RequestBody CarUpdateDto carUpdateDto) {
		Optional<Car> doc = carService.getCarById(id);
		Car newCar = new Car();
		if(doc.isPresent()) {
			newCar = convertUpdateDtoToEntity(carUpdateDto);
			log.info("Car was changed!!!");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(carService.updateCar(newCar));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Car());
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	public void deleteCar(@Valid @PathVariable("id")  Long id) {
		log.info("analising data ... ");
		carService.deleteCar(id);
	}

	public Car convertUpdateDtoToEntity(CarUpdateDto carUpdateDto) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(carUpdateDto, Car.class);
	}

}
