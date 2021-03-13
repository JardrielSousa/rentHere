package br.com.rentHere.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("v1/rent/")
public class RentHereController {
	@GetMapping
	public ResponseEntity<?> getRent(){
		return ResponseEntity.status(HttpStatus.OK).body("Hello");
	}
}
