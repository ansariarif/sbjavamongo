package com.gpch.mongo.controller;

import com.gpch.mongo.model.Reservation;
import com.gpch.mongo.service.ReservationService;
import com.gpch.mongo.service.SequenceGeneratorService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ReservationThymeleafController {

    private ReservationService reservationService;
    
    private static final Logger log = LoggerFactory.getLogger(ReservationThymeleafController.class);
    
    

    @Autowired
    public ReservationThymeleafController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    
    @Autowired
	private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("/reservations-ui")
    public String reservations(Model model) {
    	log.info("GET : /reservations --> Called");
        model.addAttribute("reservations", reservationService.getAllReservations());
        return "reservations";
    } 

    @GetMapping("/delete-reservation/{id}")
    public String removeReservation(@PathVariable("id") long id, Model model) {
    	log.info("DELETE : /reservations/{"+id+"} --> Called");
        reservationService.deleteReservationById(id);
        model.addAttribute("reservations", reservationService.getAllReservations());
        return "reservations";
    }

    @GetMapping(value = {"/edit-add-reservation/{id}", "/edit-add-reservation"})
    public String editReservation(@PathVariable("id") Optional<Long> id, Model model) {
        Reservation reservation = id.isPresent() ?
                reservationService.findReservationById(id.get()).get(): new Reservation();
        System.out.println(reservation);
        model.addAttribute("reservation", reservation);
        return "add-edit";
    }
    
    
    @PostMapping("/save-reservation")
    public String addReservation(@ModelAttribute("reservation") @Valid  Reservation reservation,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-edit";
        }
        log.info("POST : /reservations/["+reservation.getId()+"] --> Called");
        reservation.setId(sequenceGeneratorService.generateSequence(Reservation.SEQUENCE_NAME));
        reservationService.saveReservation(reservation);
        return "redirect:reservations-ui";
    }
    
    
   
    @PostMapping("/edit-reservation")
    public String editReservation(@ModelAttribute("reservation") @Valid  Reservation reservation,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-edit";
        }
        long id = reservation.getId();
        Reservation existreservation =  reservationService.findReservationById(id).get();
        existreservation.setId(id);
        existreservation.setFirstName(reservation.getFirstName());
        existreservation.setLastName(reservation.getLastName());
        existreservation.setMobile(reservation.getMobile());
        existreservation.setEmailId(reservation.getEmailId());
        existreservation.setState(reservation.getState());
        existreservation.setCity(reservation.getCity());
      //  reservation.setId(sequenceGeneratorService.generateSequence(Reservation.SEQUENCE_NAME));
        reservationService.saveReservation(existreservation);
        return "redirect:reservations-ui";
    }
    
    
    
    @GetMapping("/search")
    public String  searchByfirstName( String byparam  , String searchtext,Model model) {
    	
    	 List<Reservation> res = new ArrayList<Reservation>();
    	 System.out.println(byparam);
    	 System.out.println(searchtext);
    	switch(byparam) {
    	      case "id":
    	    	  //System.out.println("helo sflksslisi;s");
    	    	  Reservation reservation=reservationService.findbyListId(1);
    	    	  System.out.println(reservation.getEmailId());
    	    	  res.add(reservation);
    	    	  
    	    	  System.out.println(reservation);
    	    	 // model.addAttribute("reservations",reservation); Long.parseLong(searchtext)
    	    	 // return "reservations";
    	    	  break;
    	    	  
    	    	 
    	      case "firstName":
    	    	  res = reservationService.findReservationfirstName(searchtext);
    	    	  break;
    	    	  
    	      case "lastName":
    	    	  res =reservationService.findReservationlastName(searchtext);
    	    	  break;
    	    	  
    	      case "mobile":
    	    	  res =reservationService.findReservationmobile(searchtext);
    	    	  break;
    	    	  
    	      case "emailId":
    	    	  res =reservationService.findReservationemailId(searchtext);
    	    	  break;
    	    	  
    	     default:
    	    	 res =null;
    	    	 break;
    	  	  
    	}
      log.info("POST : /reservations/["+searchtext+"] --> Called");
      model.addAttribute("reservations", res);
    	
    	return "reservations";
    }
}
