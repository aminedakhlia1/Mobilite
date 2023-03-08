package tn.esprit.pidev.mobilitechdraft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.mobilitechdraft.entities.Formation;
import tn.esprit.pidev.mobilitechdraft.entities.ReservationFormation;
import tn.esprit.pidev.mobilitechdraft.entities.ReservationFormationStatus;
import tn.esprit.pidev.mobilitechdraft.repositories.ReservationFormationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationFormationServiceImplementation implements ReservationFormationService {

    @Autowired
    ReservationFormationRepository reservationFormationRepository;

    @Override
    public ReservationFormation createReservationFormation(ReservationFormation reservationFormation) {

        reservationFormation.setReservationFormationStatus(ReservationFormationStatus.ON_HOLD);
        System.out.println(reservationFormation);
        return reservationFormationRepository.save(reservationFormation);
    }


    @Override
    public Optional<ReservationFormation> getReservationFormation(Long reservationFormationId) {

        return reservationFormationRepository.findById(reservationFormationId);
    }

    @Override
    public List<ReservationFormation> getAllReservationFormation() {

        return reservationFormationRepository.findAll();
    }

    @Override
    public ReservationFormation updateReservationFormation(ReservationFormation reservationFormation) {

        return reservationFormationRepository.save(reservationFormation);
    }

    @Override
    public void deleteReservationFormation(Long reservationFormationId) {

        reservationFormationRepository.deleteById(reservationFormationId);
    }

    @Override
    public ReservationFormation approveReservationFormation(Long reservationFormationId) {

        ReservationFormation reservationFormation =reservationFormationRepository.findReservationFormationByReservationFormationId(reservationFormationId);
        reservationFormation.setReservationFormationStatus(ReservationFormationStatus.APPROVED);
        return this.updateReservationFormation(reservationFormation);
    }

    @Override
    public ReservationFormation rejectReservationFormation(Long reservationFormationId) {

        ReservationFormation reservationFormation =reservationFormationRepository.findReservationFormationByReservationFormationId(reservationFormationId);
        reservationFormation.setReservationFormationStatus(ReservationFormationStatus.REJECTED);
        return this.updateReservationFormation(reservationFormation);
    }

    @Override
    public ReservationFormation cancelReservationFormation(Long reservationFormationId) {

        ReservationFormation reservationFormation =reservationFormationRepository.findReservationFormationByReservationFormationId(reservationFormationId);
        reservationFormation.setReservationFormationStatus(ReservationFormationStatus.CANCELED);
        return this.updateReservationFormation(reservationFormation);
    }

    @Override
    public ReservationFormation findReservationFormationById(Long reservationFormationId) {

        return reservationFormationRepository.findReservationFormationByReservationFormationId(reservationFormationId);
    }

    @Override
    public ReservationFormation findReservationFormationByReservationFormationStatus(ReservationFormationStatus reservationFormationStatus) {

        return reservationFormationRepository.findReservationFormationByReservationFormationStatus(reservationFormationStatus);
    }

}
