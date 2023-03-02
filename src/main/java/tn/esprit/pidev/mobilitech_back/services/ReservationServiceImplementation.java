package tn.esprit.pidev.mobilitech_back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.mobilitech_back.entities.Reservation;
import tn.esprit.pidev.mobilitech_back.entities.StatutReservation;
import tn.esprit.pidev.mobilitech_back.repositories.ReservationRepository;

import java.util.List;

@Service
public class ReservationServiceImplementation implements ReservationService {

    @Autowired
    ReservationRepository reservationRepository;


    @Override
    public Reservation saveReservation(Reservation reservation) {
        reservation.setStatutReservation(StatutReservation.En_attente);
        System.out.println(reservation);
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation updateReservation(Long id, Reservation reservation) {
        reservation.setId(id);
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation accepterReservation(Long id) {
        Reservation reservation =reservationRepository.getById(id);
        reservation.setStatutReservation(StatutReservation.Approuvé);
        return this.updateReservation(reservation.getId(),reservation);
    }

    @Override
    public Reservation refuserReservation(Long id) {
        Reservation reservation =reservationRepository.getById(id);
        reservation.setStatutReservation(StatutReservation.Rejeté);
        return this.updateReservation(reservation.getId(),reservation);
    }

    @Override
    public Reservation annulerReservation(Long id) {
        Reservation reservation =reservationRepository.getById(id);
        reservation.setStatutReservation(StatutReservation.Annulé);
        return this.updateReservation(reservation.getId(),reservation);
    }

    @Override
    public Reservation findReservationById(Long id) {

        return reservationRepository.findReservationById(id);
    }
}