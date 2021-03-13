package br.com.rentHere.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rentHere.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
