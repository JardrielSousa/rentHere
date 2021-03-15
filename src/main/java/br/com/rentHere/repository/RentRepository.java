package br.com.rentHere.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rentHere.model.Rent;

public interface RentRepository extends JpaRepository<Rent, Long>{

}
