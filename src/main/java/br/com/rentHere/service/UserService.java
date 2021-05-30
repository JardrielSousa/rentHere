package br.com.rentHere.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.rentHere.model.User;

public interface UserService {
	public Page<User> getAllUsers(Pageable pageable);

	public Optional<User> getUserById(Long id);

	public User insertUser(User user);

	public User updateUser(User user);

	public void deleteUser(Long id);

	public User userExists(Long id);
}
