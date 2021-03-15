package br.com.rentHere.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import br.com.rentHere.dto.RentDto;
import br.com.rentHere.enums.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Rent {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date pickup;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date devolution;
	
	@Enumerated(EnumType.STRING)
	private Payment payment;
	
	@OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
	
	@OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

	public Rent convertDtoToEntity(@Valid RentDto rentDto, Car car, User user) {
		Rent rent = new Rent();
		rent.setDevolution(rentDto.getDevolution());
		rent.setPickup(rentDto.getPickup());
		rent.setPayment(rentDto.getPayment());
		rent.setUser(user);
		rent.setCar(car);
		return rent;
	}
}
