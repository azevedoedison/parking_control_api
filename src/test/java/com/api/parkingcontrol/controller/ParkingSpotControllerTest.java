package com.api.parkingcontrol.controller;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.api.parkingcontrol.model.ParkingSpotModel;
import com.api.parkingcontrol.service.ParkingSpotService;

@SpringBootTest
public class ParkingSpotControllerTest {
	
	
	private static final UUID ID = UUID.fromString("35361d85-107c-4f1f-a047-263093161c1b");
	private static final String PARKINGSPOTNUMBER = "1010";
	private static final String LICENSEPLATECAR = "EAM3J81";
	private static final String MODELCAR = "FIT";
	private static final String BRANDCAR = "HONDA";
	private static final String COLORCAR = "GREEN";
	private static final LocalDateTime REGISTRATIONDATE = LocalDateTime.now();  		
	private static final String RESPONSIBLENAME = "Edison Azevedo";		
	private static final String APARTMENT = "51";
	private static final String BLOCK = "A";	
	
	
	@InjectMocks
	private ParkingSpotController controller;
	
	@Mock
	private ParkingSpotService service;
	private ParkingSpotModel parkingSpot;
	private Optional<ParkingSpotModel> parkingSpotOptional;
	
	@BeforeEach
	void setUp() {
		startParkingSpot();
		MockitoAnnotations.openMocks(this);
	}
	
	private void startParkingSpot() {
		parkingSpot = new ParkingSpotModel(ID, 
										   PARKINGSPOTNUMBER, 
										   LICENSEPLATECAR, 
										   BRANDCAR, 
										   MODELCAR, 
										   COLORCAR, 
										   REGISTRATIONDATE, 
										   RESPONSIBLENAME, 
										   APARTMENT, 
										   BLOCK);  	
		
		parkingSpotOptional = Optional.of(new ParkingSpotModel(ID, 
				   											   PARKINGSPOTNUMBER, 
				   											   LICENSEPLATECAR, 
				   											   BRANDCAR, 
				   											   MODELCAR, 
				   											   COLORCAR, 
				   											   REGISTRATIONDATE, 
				   											   RESPONSIBLENAME, 
				   											   APARTMENT, 
				   											   BLOCK));  				
	}
	
	@Test
	void whenFindByIdThenReturnSucess() {
		Mockito.when(service.findById(Mockito.any(UUID.class))).thenReturn(parkingSpotOptional);
		
		ResponseEntity<Object> response = controller.getOneParkingSpot(ID);
		
		//Assegure pra mim que o retorno não seja nulo.
		Assertions.assertNotNull(response);
	}
	// ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id") UUID id){
	

}
