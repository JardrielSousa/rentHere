package br.com.rentHere.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.rentHere.dto.UserDto;
import br.com.rentHere.dto.UserUpdateDto;
import br.com.rentHere.model.User;
import br.com.rentHere.service.UserService;
import br.com.rentHere.serviceImpl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("v1/rent/user")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserServiceImpl userService) {
		this.userService = userService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
	public User saveUser(@Valid @RequestBody UserDto userDto){
		log.info("analising data ... ");
		User user = new User().convertDtoToEntity(userDto);
		return userService.insertUser(user);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
	public User updateUser(@Valid @PathVariable Long id , @RequestBody UserUpdateDto userUpdateDto){
		log.info("analising data ... ");
		User user = convertUpdateDtoToEntity(userUpdateDto);
		return userService.updateUser(user);
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
	public void updateUser(@Valid @PathVariable Long id){
		log.info("analising data ... ");
		userService.deleteUser(id);
	}

	public User convertUpdateDtoToEntity(UserUpdateDto userUpdateDto) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(userUpdateDto, User.class);
	}
}
