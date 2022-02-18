package com.gpch.mongo.service;

import com.gpch.mongo.model.Reservation;
import com.gpch.mongo.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation saveReservation(Reservation reservation){
        return reservationRepository.save(reservation);
    }
 
    public Iterable<Reservation> getAllReservations(){
        return reservationRepository.findAll();
    }

    public void deleteAllReservations(){
        reservationRepository.deleteAll(); 
    }

    public void deleteReservationById(long id){
        reservationRepository.deleteById(id);
       // return id;
    }

    public Optional<Reservation> findReservationById(long id){
        return reservationRepository.findById(id);
    }
    
    public List<Reservation> findReservationfirstName(String firstName){
        return reservationRepository.findByfirstName(firstName);
    }
    
    public Reservation findbyListId(long id){
    	return  reservationRepository.findbyIdUser(id);
    } 
    
    
    public List<Reservation> findReservationlastName(String lastName){
        return reservationRepository.findBylastName(lastName);
    }
     
    public List<Reservation> findReservationmobile(String mobile){
        return reservationRepository.findBymobile(mobile);
    }
    
    public List<Reservation> findReservationemailId(String emailId){
        return reservationRepository.findByemailId(emailId);
    }
    
    
   
}
