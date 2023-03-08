package tn.esprit.pidev.mobilitechdraft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.mobilitechdraft.entities.ReservationFormation;
import tn.esprit.pidev.mobilitechdraft.entities.ReservationFormationStatus;

@Repository
public interface ReservationFormationRepository extends JpaRepository<ReservationFormation, Long> {

    ReservationFormation findReservationFormationByReservationFormationId(Long reservationFormationId);

    ReservationFormation findReservationFormationByReservationFormationStatus(ReservationFormationStatus reservationFormationStatus);
}
