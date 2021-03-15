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
import br.com.rentHere.dto.RentDto;
import br.com.rentHere.dto.RentUpdateDto;
import br.com.rentHere.model.Car;
import br.com.rentHere.model.Rent;
import br.com.rentHere.model.User;
import br.com.rentHere.service.CarService;
import br.com.rentHere.service.RentService;
import br.com.rentHere.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("v1/rent/")
public class RentHereController {
	private RentService rentService;
	private CarService carService;
	private UserService userService;
	
	public RentHereController(RentService rentService,CarService carService,UserService userService) {
		this.rentService = rentService;
		this.carService = carService;
		this.userService = userService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Page<Rent> getAllRent(Pageable pageable) {
		return rentService.getAllRents(pageable);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Rent getRentById(@PathVariable("id") Long id, Pageable pageable) {
		Optional<Rent> rent = rentService.getRentById(id);
		return rent.get();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Rent saveCar(@Valid @RequestBody RentDto rentDto) {
		log.info("analising data ... ");
		Optional<Car> car = getCarId(rentDto.getCarId());
		Optional<User> user = getUserId(rentDto.getUserId());
		Rent rent = new Rent().convertDtoToEntity(rentDto,car.get(),user.get());
		return rentService.insertRent(rent);
	}

	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity<?> updateRent(@Valid @PathVariable("id") Long id, @RequestBody RentUpdateDto rentUpdateDto) {
		Optional<Rent> rent = rentService.getRentById(id);
		Optional<Car> car = getCarId(rentUpdateDto.getCarId());
		Optional<User> user = getUserId(rentUpdateDto.getUserId());
		Rent newRent = new Rent();
		if(rent.isPresent()) {
			newRent = convertUpdateDtoToEntity(rentUpdateDto,car.get(),user.get());
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(rentService.updateRent(newRent));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Rent());
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	public void updateRent(@Valid @PathVariable Long id) {
		log.info("analising data ... ");
		rentService.deleteRent(id);
	}

	public Rent convertUpdateDtoToEntity(RentUpdateDto rentUpdateDto, Car car, User user) {
		Rent rent = new Rent();
		rent.setId(rentUpdateDto.getId());
		rent.setDevolution(rentUpdateDto.getDevolution());
		rent.setPickup(rentUpdateDto.getPickup());
		rent.setPayment(rentUpdateDto.getPayment());
		rent.setUser(user);
		rent.setCar(car);
		return rent;
	}
	
	private Optional<Car> getCarId(Long id) {
		Optional<Car> car = carService.getCarById(id);
		return car;
	}
	

	private Optional<User> getUserId(Long id) {
		return userService.getUserById(id);
	}


}
