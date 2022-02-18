package com.gpch.mongo;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
//import static org.junit.Assert.assertEquals;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.assertj.core.internal.bytebuddy.NamingStrategy.SuffixingRandom.BaseNameResolver.ForGivenType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.format.datetime.joda.LocalDateTimeParser;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gpch.mongo.controller.ReservationThymeleafController;
import com.gpch.mongo.model.Reservation;
import com.gpch.mongo.repository.ReservationRepository;
import com.gpch.mongo.service.ReservationService;


//@WebMvcTest(ReservationThymeleafController.class)
@ExtendWith(MockitoExtension.class)
//@SpringBootTest
class MongoApplicationTests {
	
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	@InjectMocks
	ReservationService reservationService;
	
	@Mock
	ReservationRepository reservationRepo;
	
	
	Reservation record1 = new Reservation(1, "arif", "ansari", "1234567890", "arif@gmail.com", "maharasthra", "thane");
	Reservation record2 = new Reservation(2, "fira", "irasna", "0987654321", "fira@gmail.com", "maharasthra", "mumbai");
	Reservation record3 = new Reservation(3, "messi", "lional", "10101010", "messif@gmail.com", "maharasthra", "navi mumbai");  

	
	//List<Reservation> records = new ArrayList<>(Arrays.asList(record1, record2, record3));
	
	@Test
	void contextLoads() {
	}
	
	
	@Test
	public void getAllRecords_success() throws Exception {
		List<Reservation> records = new ArrayList<>(Arrays.asList(record1, record2, record3));
		when(reservationRepo.findAll()).thenReturn(records);
	    List<Reservation> userList1 =(List<Reservation>) reservationService.getAllReservations();
	 
	    assertThat(records).isEqualTo(userList1);
	     
	}
	
	@Test
	public void deleteRecordsById() throws Exception {
		//List<Reservation> records = new ArrayList<>(Arrays.asList(record1, record2, record3));
		//when(reservationService.deleteReservationById((long)2)).thenReturn(record2.getId());
	   // List<Reservation> userList1 =(List<Reservation>) reservationService.getAllReservations();
		reservationService.deleteReservationById((long)2);
		verify(reservationRepo,times(1)).deleteById((long) 2);
	   // assertThat(2).isEqualTo(reservationService.deleteReservationById((long)2));
	     
	}
	
	@Test
	public void deleteRecordsByIdAll() throws Exception {
		//List<Reservation> records = new ArrayList<>(Arrays.asList(record1, record2, record3));
		//when(reservationService.deleteReservationById((long)2)).thenReturn(record2.getId());
	   // List<Reservation> userList1 =(List<Reservation>) reservationService.getAllReservations();
		reservationService.deleteAllReservations();
		verify(reservationRepo,times(1)).deleteAll();
	   // assertThat(2).isEqualTo(reservationService.deleteReservationById((long)2));
	     
	}
	
	
/*	 @Test
	 public void testSearchUsersByName() throws Exception {
		 List<Reservation> recordList = new ArrayList<>(Arrays.asList(record1, record2, record3));
		 List<Reservation> searchUserList =  recordList.stream().filter(u -> u.getFirstName().equals("arif")).collect(Collectors.toList());
	    when(reservationService.findReservationfirstName("arif")).thenReturn(recordList);
	   // assertThat(records).isEqualTo(searchUserList);
	    assertThat(searchUserList).isEqualTo(reservationService.findReservationfirstName("arif"));
	   // assertEquals(searchUserList,reservationService.findReservationfirstName("AAA"));
	 }
	

     @Test
	 public void testSearchUsersBylastName() throws Exception {
		 List<Reservation> records2 = new ArrayList<>(Arrays.asList(record1, record2, record3));
		 List<Reservation> searchUserList =  records2.stream().filter(u -> u.getLastName().equals("AAA")).collect(Collectors.toList());
	    when(reservationService.findReservationlastName("AAA")).thenReturn(records2);
	   // assertThat(records).isEqualTo(searchUserList);
	    assertThat(searchUserList).isEqualTo(reservationService.findReservationlastName("AAA"));
	   // assertEquals(searchUserList,reservationService.findReservationfirstName("AAA"));
	 }  */
     
     
	  
	 @Test
		public void saveAllRecords() throws Exception {
			//List<Reservation> records = new ArrayList<>(Arrays.asList(record1, record2, record3));
		 Reservation newReservation = new Reservation(4,"messi", "lional", "10101010", "messif@gmail.com", "maharasthra", "navi mumbai");
			//when(reservationService.saveReservation()).thenReturn(newReservation);
		    //List<Reservation> userList1 =(List<Reservation>) reservationService.getAllReservations();
		    Reservation saved =  reservationService.saveReservation(newReservation);
		    verify(reservationRepo,times(1)).save(newReservation);
		  //  assertThat(records).isEqualTo(userList1);
		     
		}
	 
	 @Test
		public void findRecordsById() throws Exception {
			//List<Reservation> records = new ArrayList<>(Arrays.asList(record1, record2, record3));
		 Reservation newReservation = new Reservation(4,"messi", "lional", "10101010", "messif@gmail.com", "maharasthra", "navi mumbai");
		final Optional<Reservation> excepted = reservationService.findReservationById(4);
		assertThat(excepted).isNotNull();
			
		     
		}
	 
	 
	    @Test
		public void findRecordsByfistName() throws Exception {
			//List<Reservation> records = new ArrayList<>(Arrays.asList(record1, record2, record3));
		 //Reservation newReservation = new Reservation(4,"messi", "lional", "10101010", "messif@gmail.com", "maharasthra", "navi mumbai");
		 
		final List<Reservation> excepted = reservationService.findReservationfirstName("meesi");
		assertThat(excepted).isNotNull(); 
			
		     
		}

	    

	    @Test
		public void findRecordsBylastName() throws Exception {
			//List<Reservation> records = new ArrayList<>(Arrays.asList(record1, record2, record3));
		 //Reservation newReservation = new Reservation(4,"messi", "lional", "10101010", "messif@gmail.com", "maharasthra", "navi mumbai");
		 
		final List<Reservation> excepted = reservationService.findReservationlastName("ansari");
		assertThat(excepted).isNotNull(); 
			
		     
		}
	    
	    
	    @Test
		public void findListsByMobile() throws Exception {
			//List<Reservation> records = new ArrayList<>(Arrays.asList(record1, record2, record3));
		     Reservation newReservation = new Reservation(4,"messi", "lional", "10101010", "messif@gmail.com", "maharasthra", "navi mumbai");
		     final List<Reservation> excepted = reservationService.findReservationmobile("101010");
		     assertThat(excepted).isNotNull();
			
		     
		}
 	
	    @Test
		public void findListsByEmail() throws Exception {
			//List<Reservation> records = new ArrayList<>(Arrays.asList(record1, record2, record3));
		     Reservation newReservation = new Reservation(4,"messi", "lional", "10101010", "messif@gmail.com", "maharasthra", "navi mumbai");
		     final List<Reservation> excepted = reservationService.findReservationemailId("messi@gmail.com");
		     assertThat(excepted).isNotNull();
			
		     
		}
	    
	    
	    List<Reservation> records = new ArrayList<>();
	    
	    @BeforeEach
	    public void insertRecords() {
	          records.add(record1);
	          records.add(record2);
	          records.add(record3);
	    }
	    
	    @Test
		public void findListsById() throws Exception {
			 //List<Reservation> records = new ArrayList<>(Arrays.asList(record1, record2, record3));
		     Reservation newReservation = new Reservation(4,"messi", "lional", "10101010", "messif@gmail.com", "maharasthra", "navi mumbai");
		      records.add(newReservation);
		    // assertThat(excepted).isNotNull();
		     List<Reservation> excepted = records.stream().filter(v -> v.getId() == 4).collect(Collectors.toList());
		     when(reservationService.findbyListId(4)).thenReturn(newReservation);
		    // List<Reservation> excepted = reservationService.findbyListId(2);
		  
		     assertThat(excepted.get(0)).isEqualTo(reservationService.findbyListId(4));
			
		     
		}
	    
	    
	  /*  @Test
		public void reservation() throws Exception {
	    	List<Reservation> records = new ArrayList<>(Arrays.asList(record1, record2, record3));
	    	Mockito.when(reservationService.getAllReservations()).thenReturn(records);
	    	 mockMvc.perform(MockMvcRequestBuilders.get("/reservations-ui").contentType(MediaType.APPLICATION_JSON))
	    	      .andExpect(status().isOk())
	    	      .andExpect(jsonPath("$", hasSize(3))).andExpect(jsonPath("$[2].fisrtnName", is("messi")));
		     
		}
	    
	    
 	*/
 	
     
  
	 
	
	
	
}
