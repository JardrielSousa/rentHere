package br.com.rentHere.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.modelmapper.ModelMapper;

import br.com.rentHere.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String email;
	private String phoneNumber;
	
	public User setTOEntity(UserDto userDto) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(userDto, User.class);
	}
}
