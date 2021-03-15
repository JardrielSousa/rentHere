package br.com.rentHere.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	@NotBlank(message =  "name is mandary")
	@NotNull(message =  "name is mandary")
	private String name;
	@Email(message = "email is invalid" )
	@NotBlank
	private String email;
	@NotNull
	@NotBlank
	private String phoneNumber;
}
