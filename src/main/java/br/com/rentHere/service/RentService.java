package br.com.rentHere.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.rentHere.model.Rent;

public interface RentService {

	public Page<Rent> getAllRents(Pageable pageable);

	public Optional<Rent> getRentById(Long id);

	public Rent insertRent(Rent rent);

	public Rent updateRent(Rent rent);

	public void deleteRent(Long id);

	public Rent rentExists(Long id);
}
