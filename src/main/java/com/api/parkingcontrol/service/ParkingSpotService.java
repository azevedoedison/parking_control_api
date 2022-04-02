package com.api.parkingcontrol.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.parkingcontrol.model.ParkingSpotModel;
import com.api.parkingcontrol.repository.ParkingSpotRepository;


@Service
public class ParkingSpotService {
	
	
	final ParkingSpotRepository parkingSpotRepository;
	
	public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
		this.parkingSpotRepository = parkingSpotRepository;
	}

	@Transactional
	public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {		
		parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));		
		return parkingSpotRepository.save(parkingSpotModel);
	}

	public boolean existsByLicensePlateCar(String licensePlateCar) {		
		return parkingSpotRepository.existsByLicensePlateCar(licensePlateCar);
	}	
	
	public boolean existsByParkingSpotNumber(String parkingSpotNumber) {		
		return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
	}
	
	public boolean existsByApartmentAndBlock(String apartament, String block) {
		return parkingSpotRepository.existsByApartmentAndBlock(apartament, block);		
	}

	public Page<ParkingSpotModel> findAll(Pageable pageable) {		
		return parkingSpotRepository.findAll(pageable);
	}

	@Transactional
	public Optional<ParkingSpotModel> findById(UUID id) {
		return parkingSpotRepository.findById(id);
	}		

	public void delete(ParkingSpotModel parkingSpotModel) {
		parkingSpotRepository.delete(parkingSpotModel);		
	}
	
}
