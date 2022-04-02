package com.api.parkingcontrol.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import com.api.parkingcontrol.model.ParkingSpotModel;
import com.api.parkingcontrol.repository.ParkingSpotRepository;


@SpringBootTest
public class ParkingSpotServiceTest {
	
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
	
	@Autowired
	private ParkingSpotService service;
	
	@MockBean
	private ParkingSpotRepository repository;
	
	private ParkingSpotModel parkingSpot;	
	
	@Mock
	private Pageable pageable;
	private Page<ParkingSpotModel> page;
	
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
		
		pageable = PageRequest.of(0, 2);
		page = new PageImpl<>(List.of(parkingSpot), pageable, 1);
	}	
	
	@Test
	void deleteWithSuccess() {
		Mockito.when(repository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(parkingSpot));	
		Mockito.doNothing().when(repository).delete(parkingSpot);
		
		service.delete(parkingSpot);
	}
	
	@Test
	void whenFindParkingSpotByIDThenReturnAnParkingSpotInstance() {			
		Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(parkingSpot));	
		
		Optional<ParkingSpotModel> response = service.findById(ID);		
		
		/*Assegure pra mim que o meu response não será nulo*/
		Assertions.assertNotNull(response);		
		/*Assegure pra mim que o meu retorno será um objeto optional */
		Assertions.assertEquals(java.util.Optional.class, response.getClass());				
		/*Assegure pra mim que o id que eu pesquisei é o ID retornado*/		
		Assertions.assertEquals(ID, response.get().getId());	
	}
	
	@Test
	void whenFindAllParkingSpotsThenReturnAnListOfParkingSpot() {
		/*Mock consulta e Retorne uma lista de um parkingSpot*/			   
		Mockito.when(repository.findAll(pageable)).thenReturn(page);
     
		Page<ParkingSpotModel> response = service.findAll(pageable);
 
		Assertions.assertNotNull(response);
	    Assertions.assertEquals(page, response);
	    Mockito.verify(repository, Mockito.times(1)).findAll(Mockito.any(Pageable.class));			
	}
	
	@Test
	void whenSaveThenReturnAnInstanceOfParkingSpot() {
		Mockito.when(repository.save(Mockito.any())).thenReturn(parkingSpot);
		
		ParkingSpotModel response = service.save(parkingSpot);
		
		/*Assgure pra mim que a classe que retorná é a classe esperada*/
		Assertions.assertEquals(ParkingSpotModel.class, response.getClass());		
		/*Assegure pra mim que a resposta não será nula*/
		Assertions.assertNotNull(response);		
		/*Assegure pra mim que o responsável retornado tem o mesmo nome do responsável que eu gravei*/
		Assertions.assertEquals(RESPONSIBLENAME, response.getResponsibleName());		
		/*Assegure pra mim que o apartamento retornado é o mesmo apartamento que eu gravei*/
		Assertions.assertEquals(APARTMENT, response.getApartment());	
		/*Assegure pra mim que o bloco retornado é o mesmo bloco que eu gravei*/
		Assertions.assertEquals(BLOCK, response.getBlock());			
	}
	
	
	@Test
	void whenToFindAParkingSpotByNumberAndExistsThenReturnTrue(){
		Mockito.when(repository.existsByParkingSpotNumber(Mockito.eq(PARKINGSPOTNUMBER))).thenReturn(true);
		
		Boolean response = service.existsByParkingSpotNumber(PARKINGSPOTNUMBER);
		
		/*Assegure pra mim que a resposta não será nula*/
		Assertions.assertNotNull(response);
		/*Assegure pra mim que a resposta é booleana*/
		Assertions.assertEquals(Boolean.class, response.getClass());	
	}
	
	@Test
	void whenToFindAParkingSpotByApartmentAndBlockAndExistsThenReturnTrue(){
		Mockito.when(repository.existsByApartmentAndBlock(Mockito.eq(APARTMENT),Mockito.eq(BLOCK)))
			.thenReturn(true);
		
		Boolean response = service.existsByApartmentAndBlock(APARTMENT, BLOCK);
		
		/*Assegure pra mim que a resposta não será nula*/
		Assertions.assertNotNull(response);
		/*Assegure pra mim que a resposta é booleana*/
		Assertions.assertEquals(Boolean.class, response.getClass());
	}	
	
	@Test
	void whenToFindAParkingSpotByLicensePlateCarAndExistsThenReturnTrue(){
		Mockito.when(repository.existsByLicensePlateCar(Mockito.eq(LICENSEPLATECAR))).thenReturn(true);
		
		Boolean response = service.existsByLicensePlateCar(LICENSEPLATECAR);
		
		/*Assegure pra mim que a resposta não será nula*/
		Assertions.assertNotNull(response);
		/*Assegure pra mim que a resposta é booleana*/
		Assertions.assertEquals(Boolean.class, response.getClass());	
	}
	
	


}
