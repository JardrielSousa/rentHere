package br.com.rentHere.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarUpdateDto {
	@NotNull
	private Long id;
	@NotBlank(message = "plaque is mandary")
	@NotNull(message = "plaque is mandary")
	private String plaque;
	
	@NotBlank(message = "model is mandary")
	@NotNull(message = "name is mandary")
	private String model;
	
	@NotNull(message = "rentPrice is mandary")
	private Double rentPrice;
	
	@NotNull(message = "quantity is mandary")
	private Long quantity;
}
