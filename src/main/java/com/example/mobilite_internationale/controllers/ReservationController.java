package com.example.mobilite_internationale.controllers;

import com.example.mobilite_internationale.entities.Accommodation;
import com.example.mobilite_internationale.entities.Opportunity;
import com.example.mobilite_internationale.entities.Reservation;
import com.example.mobilite_internationale.entities.User;
import com.example.mobilite_internationale.interfaces.ReservationInterface;
import com.example.mobilite_internationale.repositories.AccommodationRepository;
import com.example.mobilite_internationale.services.AccommodationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Reservation")
public class ReservationController {
    @Autowired
    ReservationInterface reservationService;

    @Autowired
    AccommodationRepository accRepo;

    @Autowired
    AccommodationServiceImpl accService;

    //ajout
  @PostMapping("/add-reservation/{idaccommodation}/{userId}")
  public ResponseEntity<String> addReservation(@RequestBody Reservation rsv, @PathVariable Integer idaccommodation, @PathVariable  Long userId){
      String Recmondation = null;
      String ListAccomodationRecmonded="don't worry, here is a list of accommodations similar to the one you have chosen : \n";
      User user = new User(userId);
     // recuperer l'accomodation
      Accommodation a= accRepo.findById(idaccommodation).orElse(null);

      if (!reservationService.addReservation(rsv,a,user)) {
          // systeme de recommandation de chambre

          for (Accommodation acc : accService.AccommodationReccomondation(a)) {

              Recmondation += ListAccomodationRecmonded + "id: " + acc.getIdAccommodation() + " |Name: " + acc.getNameAccommodation()
                      + " |Capacity: " + acc.getCapacity() + " |Type: " + acc.getTypeAccommodation() + "\n";
          }
          return ResponseEntity.status(HttpStatus.CONFLICT).body("Reservation Refused ,  " + Recmondation + "\n.");
      }
      return ResponseEntity.ok("Reservation Accepted\n.");
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
    @GetMapping("/{userId}")
    public String getReservationsByUser(@PathVariable  Long userId) {
        User user = new User(userId);
        List<Reservation> reservations = reservationService.findReservationsByUserAndWithinAWeek(user);

        if (reservations.isEmpty()) {
            return "Aucune réservation à venir dans une semaine.";
        } else {
            StringBuilder sb = new StringBuilder("Les réservations à venir dans une semaine sont :");
            for (Reservation reservation : reservations) {
                sb.append(String.format(" %s le %s,", reservation.getIdReservation(), reservation.getCheckinDate()));
            }
            return sb.toString();
        }
    }
}
