package br.com.rentHere.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.rentHere.model.User;
import br.com.rentHere.model.dto.UserDto;
import br.com.rentHere.model.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("v1/rent/user")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
	public User getUsers(@RequestBody UserDto userDto){
		log.info("analising data ... ");
		User user = new User().setTOEntity(userDto);
		return userService.save(user);
	}

}
