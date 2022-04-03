package com.api.parkingcontrol.controller;

import static org.mockito.Mockito.times;

import java.time.LocalDateTime;
import java.util.List;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.api.parkingcontrol.dto.ParkingSpotDTO;
import com.api.parkingcontrol.model.ParkingSpotModel;
import com.api.parkingcontrol.service.ParkingSpotService;

@SpringBootTest
public class ParkingSpotControllerTest {
	
	@InjectMocks
	private ParkingPostController controller;
	
	@Mock
	private ParkingSpotService service;
	
	private ParkingSpotModel parkingSpot = new ParkingSpotModel();
	private ParkingSpotDTO   parkingSpotDTO = new ParkingSpotDTO();
	private Pageable pageable;
	private Page<ParkingSpotModel> page;
	
	private static final UUID ID = UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6");
	private static final UUID WRONGID = UUID.randomUUID();
	private static final String PARKINGSPOTNUMBER = "1010";
	private static final String LICENSEPLATECAR = "EAM3J81";
	private static final String MODELCAR = "FIT";
	private static final String BRANDCAR = "HONDA";
	private static final String COLORCAR = "GREEN";
	private static final LocalDateTime REGISTRATIONDATE = LocalDateTime.now();  		
	private static final String RESPONSIBLENAME = "Edison Azevedo";		
	private static final String APARTMENT = "51";
	private static final String BLOCK = "A";	

	private void start() {
		parkingSpot = new ParkingSpotModel(ID, PARKINGSPOTNUMBER, LICENSEPLATECAR, BRANDCAR, MODELCAR, COLORCAR, REGISTRATIONDATE, RESPONSIBLENAME, APARTMENT, BLOCK);
		parkingSpotDTO = new ParkingSpotDTO(PARKINGSPOTNUMBER, LICENSEPLATECAR, BRANDCAR, MODELCAR, COLORCAR, RESPONSIBLENAME, APARTMENT, BLOCK);
		pageable =  PageRequest.of(0, 2, Sort.by("id"));
		page = new PageImpl<>(List.of(parkingSpot), pageable, 1);
	}
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		start();		
	}
	
	
	@Test
	void whenFindByIDThenReturnNotFound() {
		Mockito.when(service.findById(Mockito.any(UUID.class))).thenReturn(Optional.empty());
		ResponseEntity<Object> response = controller.findById(WRONGID);	
		//Assegure pra mim que eu recebei um HTTPStatusCode OK
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		//Assegure pra mim que o retorno não seja nulo.
		Assertions.assertNotNull(response);
		//Assegure pra mim que a resposta do endpoint vai ter a classe do tipo ResponseEntity
		Assertions.assertEquals(ResponseEntity.class, response.getClass());		
	}
	
	@Test
	void whenFindByIDThenReturnSuccess() {
		Mockito.when(service.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(parkingSpot));
		ResponseEntity<Object> response = controller.findById(ID);		
		//Assegure pra mim que eu recebei um HTTPStatusCode OK
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		//Assegure pra mim que o retorno não seja nulo.
		Assertions.assertNotNull(response);
		//Assegure pra mim que a resposta do endpoint vai ter a classe do tipo ResponseEntity
		Assertions.assertEquals(ResponseEntity.class, response.getClass());		
	}
	
	@Test
	void whenFindAllThenReturnSuccess() {
		Mockito.when(service.findAll(pageable)).thenReturn(page);
		ResponseEntity<Page<ParkingSpotModel>> response = controller.getAllParkingSpots(pageable);		
		//Assegure pra mim que o retorno não seja nulo.
		Assertions.assertNotNull(response);
		//Assegure pra mim que eu recebei um HTTPStatusCode OK
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		//Assegure pra mim que a resposta do endpoint vai ter a classe do tipo ResponseEntity
		Assertions.assertEquals(ResponseEntity.class, response.getClass());	
		//Assegure pra mim que o total de Elementos é o total de elemntos mocado
		Assertions.assertEquals(page.getTotalElements(),response.getBody().getTotalElements());
	}
	
	@Test
	void whenDeleteParkingSpotThenReturnOK() {
		Mockito.when(service.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(parkingSpot));
		Mockito.doNothing().when(service).delete(parkingSpot);
		
		ResponseEntity<Object> response = controller.deleteParkingSpot(ID);
		
		//Assegure pra mim que eu recebei um HTTPStatusCode OK
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		//Assegure pra mim quantas vezes o delete foi chamado (tem que ter sido só uma vez)
		Mockito.verify(service, times(1)).delete(Mockito.any());
		
	}
	
	@Test
	void whenDeleteParkingSpotThenReturnNotFound() {
		Mockito.when(service.findById(Mockito.any(UUID.class))).thenReturn(Optional.empty());
		
		ResponseEntity<Object> response = controller.deleteParkingSpot(ID);
		
		//Assegure pra mim que eu recebei um HTTPStatusCode OK
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		//Assegure pra mim que o retorno não seja nulo.
		Assertions.assertNotNull(response);
		//Assegure pra mim que a resposta do endpoint vai ter a classe do tipo ResponseEntity
		Assertions.assertEquals(ResponseEntity.class, response.getClass());	
	}
	
	@Test
	void whenUpdateParkingSpotThenReturnOK() {
		Mockito.when(service.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(parkingSpot));
		
		ResponseEntity<Object> response = controller.updateParkingSpot(ID, parkingSpotDTO);	
		//Assegure pra mim que o meu Response não será nulo		
		Assertions.assertNotNull(response);		
		//Assegure pra mim que o status HTTP retornará 200 - ok
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void WhenUpdateParkingSpotThenReturnNotFound() {
		Mockito.when(service.findById(Mockito.any(UUID.class))).thenReturn(Optional.empty());
		
		ResponseEntity<Object> response = controller.updateParkingSpot(ID, parkingSpotDTO);	
		
		//Assegure pra mim que eu recebei um HTTPStatusCode OK
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		//Assegure pra mim que o retorno não seja nulo.
		Assertions.assertNotNull(response);
		//Assegure pra mim que a resposta do endpoint vai ter a classe do tipo ResponseEntity
		Assertions.assertEquals(ResponseEntity.class, response.getClass());	
	}
	
	@Test
	void whenSaveParkingSpotThenReturnCreated() {
		Mockito.when(service.save(Mockito.any())).thenReturn(parkingSpot);
		
		ResponseEntity<Object> response = controller.saveParkingSpot(parkingSpotDTO);
		
		//Assegure pra mim que a resposta do endpoint vai ter a classe do tipo ResponseEntity
		Assertions.assertEquals(ResponseEntity.class, response.getClass());		
		//Assegure pra mim que o status HTTP retornará 201 - Created
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	@Test
	void whenSaveParkingSpotANDExistsLicensePlateCarThenReturnConflict(){
		Mockito.when(service.existsByLicensePlateCar(Mockito.any())).thenReturn(true);
		
		ResponseEntity<Object> response = controller.saveParkingSpot(parkingSpotDTO);
		
		//Assegure pra mim que o status HTTP retornará 409 - Conflict
		Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
		//Assegure pra mim que a resposta do endpoint vai ter a classe do tipo ResponseEntity
		Assertions.assertEquals(ResponseEntity.class, response.getClass());	
		
	}
	@Test
	void whenSaveParkingSpotANDExistsByParkingSpotNumberThenReturnConflict(){
		Mockito.when(service.existsByParkingSpotNumber(Mockito.any())).thenReturn(true);
		
		ResponseEntity<Object> response = controller.saveParkingSpot(parkingSpotDTO);
		
		//Assegure pra mim que o status HTTP retornará 409 - Conflict
		Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
		//Assegure pra mim que a resposta do endpoint vai ter a classe do tipo ResponseEntity
		Assertions.assertEquals(ResponseEntity.class, response.getClass());	
		
	}
	@Test
	void whenSaveParkingSpotANDexistsByApartmentAndBlockThenReturnConflict(){
		Mockito.when(service.existsByApartmentAndBlock(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
		
		ResponseEntity<Object> response = controller.saveParkingSpot(parkingSpotDTO);
		
		//Assegure pra mim que o status HTTP retornará 409 - Conflict
		Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
		//Assegure pra mim que a resposta do endpoint vai ter a classe do tipo ResponseEntity
		Assertions.assertEquals(ResponseEntity.class, response.getClass());	
		
	}
	
	
	
}
