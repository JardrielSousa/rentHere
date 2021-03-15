package br.com.rentHere.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rentHere.model.Car;

public interface CarRepository extends JpaRepository<Car, Long>{

}
