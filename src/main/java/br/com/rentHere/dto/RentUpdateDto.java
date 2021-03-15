package br.com.rentHere.dto;

import java.util.Date;

import br.com.rentHere.enums.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentUpdateDto {
	private Long id;
	private Date pickup;
	private Date devolution;
	private Payment payment;
	private Long CarId;
	private Long UserId;

}
