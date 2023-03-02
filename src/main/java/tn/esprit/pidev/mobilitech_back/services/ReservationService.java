package tn.esprit.pidev.mobilitech_back.services;

import tn.esprit.pidev.mobilitech_back.entities.Reservation;

import java.util.List;

public interface ReservationService {
    Reservation saveReservation(Reservation reservation);
    List<Reservation> getReservations();
    Reservation updateReservation(Long id, Reservation reservation);
    Reservation accepterReservation(Long id);
    Reservation refuserReservation(Long id);
    Reservation annulerReservation(Long id);
    Reservation findReservationById(Long id);
}
