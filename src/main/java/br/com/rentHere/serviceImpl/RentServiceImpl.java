package br.com.rentHere.serviceImpl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.rentHere.exception.ResourceNotFoundException;
import br.com.rentHere.model.Rent;
import br.com.rentHere.repository.RentRepository;
import br.com.rentHere.service.RentService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RentServiceImpl implements RentService{
	
	private  RentRepository rentRepository;

	public RentServiceImpl(RentRepository rentRepository) {
		this.rentRepository = rentRepository;
	}

	public Page<Rent> getAllRents(Pageable pageable){
		return rentRepository.findAll(pageable);
	}
	
	public Optional<Rent> getRentById(Long id){
		Rent rent = rentExists(id);
		return Optional.of(rent) ;
	}
	
	public Rent insertRent(Rent rent) {
		return rentRepository.save(rent);
	}
	public Rent updateRent(Rent rent) {
		return insertRent(rent);
	}
	@Transactional
	public void deleteRent(Long id) {
		rentRepository.deleteById(id);
	}
	
	public Rent rentExists(Long id) {
		log.info("verifing if exists");
		Optional<Rent> rent = rentRepository.findById(id);
		if(rent.isEmpty()) {
			throw new ResourceNotFoundException("not found for id: "+id);
		}
		return rent.get();
	}
}
