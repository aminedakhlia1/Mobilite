package tn.esprit.pidev.mobilitech_back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.mobilitech_back.entities.Reservation;
import tn.esprit.pidev.mobilitech_back.entities.StatutReservation;
import tn.esprit.pidev.mobilitech_back.repositories.ReservationRepository;
import tn.esprit.pidev.mobilitech_back.services.ReservationService;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    ReservationRepository reservationRepository;

    @RequestMapping(value = "/saveReservation", method = RequestMethod.POST)
    public Reservation saveReservation(@RequestBody Reservation reservation) {
        return reservationService.saveReservation(reservation);
    }

    @RequestMapping(value = "/allReservations", method = RequestMethod.GET)
    public List<Reservation> getReservations ()  {
        return  reservationService.getReservations();
    }

    @RequestMapping(value = "/annulerReservation/{id}", method = RequestMethod.PUT)
    public Reservation annulerReservation(@PathVariable(name = "id") Long id) {
        return  reservationService.annulerReservation(id);
    }

    @RequestMapping(value = "/accepterReservation/{id}", method = RequestMethod.PUT)
    public Reservation accepterReservation(@PathVariable(name = "id") Long id) {
        return  reservationService.accepterReservation(id);
    }

    @RequestMapping(value = "/refuserReservation/{id}", method = RequestMethod.PUT)
    public Reservation refuserReservation(@PathVariable(name = "id") Long id) {
        return  reservationService.refuserReservation(id);
    }

    @RequestMapping(value = "/findReservationbyStatut", method = RequestMethod.GET)
    public List<Reservation>findbyStatut(@RequestParam(name = "statut") StatutReservation statut) {
        return reservationRepository.findReservationsByStatutReservation(statut);
    }
    @GetMapping(value = "/findReservationById/{id}")
    public Reservation findRservationById  (@PathVariable(name = "id") Long id) {
        return  reservationService.findReservationById(id);
    }
}