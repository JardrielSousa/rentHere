package br.com.rentHere.model.service;

import org.springframework.stereotype.Service;

import br.com.rentHere.model.User;
import br.com.rentHere.repository.UserRepository;

@Service
public class UserService {
	
	private  UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}

}
