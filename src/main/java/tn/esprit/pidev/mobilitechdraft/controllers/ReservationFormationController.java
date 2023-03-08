package tn.esprit.pidev.mobilitechdraft.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.mobilitechdraft.entities.Formation;
import tn.esprit.pidev.mobilitechdraft.entities.ReservationFormation;
import tn.esprit.pidev.mobilitechdraft.entities.ReservationFormationStatus;
import tn.esprit.pidev.mobilitechdraft.services.ReservationFormationService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservationFormations")
public class ReservationFormationController {

    @Autowired
    ReservationFormationService reservationFormationService;


    @RequestMapping(value = "/create-reservation-formation", method = RequestMethod.POST)
    public ReservationFormation createReservationFormation(@RequestBody ReservationFormation reservationFormation) {

        return reservationFormationService.createReservationFormation(reservationFormation);
    }


    @GetMapping("/get-reservation-formation/{reservationFormationId}")
    public Optional<ReservationFormation> getReservationFormation(@PathVariable("reservationFormationId") Long reservationFormationId) {

        return reservationFormationService.getReservationFormation(reservationFormationId);
    }

    @RequestMapping(value = "/get-all-reservation-formation", method = RequestMethod.GET)
    public List<ReservationFormation> getAllReservationFormation ()  {

        return reservationFormationService.getAllReservationFormation();
    }


    @PutMapping("/update-reservation-formation/{reservationFormationId}")
    public ReservationFormation updateReservationFormation(@PathVariable("reservationFormationId") Long reservationFormationId, @RequestBody ReservationFormation reservationFormation) {

        reservationFormation.setReservationFormationId(reservationFormationId);
        return reservationFormationService.updateReservationFormation(reservationFormation);
    }


    @DeleteMapping("/delete-reservation-formation/{reservationFormationId}")
    public void deleteReservationFormation(@PathVariable("reservationFormationId") Long reservationFormationId) {

        reservationFormationService.deleteReservationFormation(reservationFormationId);
    }


    @RequestMapping(value = "/approve-reservation-formation/{reservationFormationId}", method = RequestMethod.PUT)
    public ReservationFormation approveReservationFormation(@PathVariable(name = "reservationFormationId") Long reservationFormationId) {

        return  reservationFormationService.approveReservationFormation(reservationFormationId);
    }

    @RequestMapping(value = "/reject-reservation-formation/{reservationFormationId}", method = RequestMethod.PUT)
    public ReservationFormation rejectReservationFormation(@PathVariable(name = "reservationFormationId") Long reservationFormationId) {

        return  reservationFormationService.rejectReservationFormation(reservationFormationId);
    }


    @RequestMapping(value = "/cancel-reservation-formation/{reservationFormationId}", method = RequestMethod.PUT)
    public ReservationFormation cancelReservationFormation(@PathVariable(name = "reservationFormationId") Long reservationFormationId) {

        return  reservationFormationService.cancelReservationFormation(reservationFormationId);
    }


    @GetMapping(value = "/find-reservation-formation-by-status/{reservationFormationStatus}")
    public ReservationFormation findReservationFormationByReservationFormationStatus  (@PathVariable(name = "reservationFormationStatus") ReservationFormationStatus reservationFormationStatus) {

        return  reservationFormationService.findReservationFormationByReservationFormationStatus(reservationFormationStatus);
    }

}
