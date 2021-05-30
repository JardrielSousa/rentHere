package br.com.rentHere.serviceImpl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.rentHere.exception.ResourceNotFoundException;
import br.com.rentHere.model.User;
import br.com.rentHere.repository.UserRepository;
import br.com.rentHere.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService{
	
	private  UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Page<User> getAllUsers(Pageable pageable){
		return userRepository.findAll(pageable);
	}
	
	public Optional<User> getUserById(Long id){
		User user = userExists(id);
		return Optional.of(user) ;
	}
	
	public User insertUser(User user) {
		return userRepository.save(user);
	}
	public User updateUser(User user) {
		return insertUser(user);
	}
	@Transactional
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
	
	public User userExists(Long id) {
		log.info("verifing if exists");
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty()) {
			throw new ResourceNotFoundException("not found for id: "+id);
		}
		return user.get();
	}
}
