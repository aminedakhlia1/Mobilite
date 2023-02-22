package com.example.mobilite_internationale.controllers;

import com.example.mobilite_internationale.entities.Opportunity;
import com.example.mobilite_internationale.entities.Reservation;
import com.example.mobilite_internationale.interfaces.ReservationInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Reservation")
public class ReservationController {
    @Autowired
    ReservationInterface reservationService;

    //ajout
  @PostMapping("/add-reservation")
  public Reservation addReservation(@RequestBody Reservation rsv){
      //return reservationService.addReservation(rsv);
      Reservation reservation= reservationService.addReservation(rsv);
      return  reservation;
  }

    @GetMapping("/retrieve-all-reservation")
    public List<Reservation> retrieveAllResevations() {
        List<Reservation> reservations = reservationService.retrieveAllReservation();
        return reservations;
    }

    @GetMapping("/retrieve-reservation/{id-reservation}")
    public Reservation retrieveReservationById(@PathVariable("id-reservation") Integer idReservation) {
        return reservationService.retrieveReservation(idReservation);
    }

    @PutMapping("/update-reservation/{id-reservation}")
    public Reservation updateReservation(@PathVariable("id-reservation") Integer idReservation,@RequestBody Reservation rsv) {
     Reservation reservation = reservationService.updateReservation(idReservation,rsv);
        return reservation;
    }

    @DeleteMapping("/remove-reservation/{id-reservation}")
    public void removereservation(@PathVariable("id-reservation") Integer idReservation) {
        reservationService.removeReservation(idReservation);
    }

}
