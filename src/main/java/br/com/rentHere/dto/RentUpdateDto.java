package br.com.rentHere.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.rentHere.enums.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentUpdateDto {
	@NotBlank(message = "id is mandary")
	@NotNull(message = "id is mandary")
	private Long id;
	@NotBlank(message = "pickup is mandary")
	@NotNull(message = "pickup is mandary")
	private Date pickup;
	@NotBlank(message = "devolution is mandary")
	@NotNull(message = "devolution is mandary")
	private Date devolution;
	@NotBlank(message = "payment is mandary")
	@NotNull(message = "payment is mandary")
	private Payment payment;
	@NotBlank(message = "CarId is mandary")
	@NotNull(message = "CarId is mandary")
	private Long CarId;
	@NotBlank(message = "UserId is mandary")
	@NotNull(message = "UserId is mandary")
	private Long UserId;

}
