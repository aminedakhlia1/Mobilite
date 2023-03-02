package tn.esprit.pidev.mobilitech_back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tn.esprit.pidev.mobilitech_back.entities.Reservation;
import tn.esprit.pidev.mobilitech_back.entities.StatutReservation;

import java.util.List;

@RepositoryRestResource
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findReservationsByStatutReservation(StatutReservation statutRdv);
    Reservation findReservationById(Long id);
}