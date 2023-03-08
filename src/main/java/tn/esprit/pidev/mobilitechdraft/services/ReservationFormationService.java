package tn.esprit.pidev.mobilitechdraft.services;

import tn.esprit.pidev.mobilitechdraft.entities.Formation;
import tn.esprit.pidev.mobilitechdraft.entities.ReservationFormation;
import tn.esprit.pidev.mobilitechdraft.entities.ReservationFormationStatus;

import java.util.List;
import java.util.Optional;

public interface ReservationFormationService {

    ReservationFormation createReservationFormation(ReservationFormation reservationFormation);

    Optional<ReservationFormation> getReservationFormation(Long reservationFormationId);

    List<ReservationFormation> getAllReservationFormation();

    ReservationFormation updateReservationFormation(ReservationFormation reservationFormation);

    void deleteReservationFormation(Long reservationFormationId);

    ReservationFormation approveReservationFormation(Long reservationFormationId);

    ReservationFormation rejectReservationFormation(Long reservationFormationId);

    ReservationFormation cancelReservationFormation(Long reservationFormationId);

    ReservationFormation findReservationFormationById(Long reservationFormationId);

    ReservationFormation findReservationFormationByReservationFormationStatus(ReservationFormationStatus reservationFormationStatus);
}
