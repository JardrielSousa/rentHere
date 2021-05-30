package br.com.rentHere.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.modelmapper.ModelMapper;

import br.com.rentHere.dto.CarDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Audited
@AuditTable(value = "car_aud")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String plaque;
	private String model;
	private Double rentPrice;
	private Long quantity;
	
	public Car convertDtoToEntity(CarDto cadDto) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(cadDto, Car.class);
	}

	public Car(Long id) {
		this.id = id;
	}
}
