package com.example.mobilite_internationale.services;

import com.example.mobilite_internationale.entities.Reservation;
import com.example.mobilite_internationale.interfaces.ReservationInterface;
import com.example.mobilite_internationale.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationInterface {

    @Autowired
    ReservationRepository reservationrepo;

    @Override
    public Reservation addReservation(Reservation reservation) {
       return reservationrepo.save(reservation);
    }

    @Override
    public List<Reservation> retrieveAllReservation() {
        return reservationrepo.findAll();
    }

    @Override
    public Reservation retrieveReservation(Integer idReservation) {
        return reservationrepo.findById(idReservation).orElse(null);
    }

    @Override
    public Reservation updateReservation(Integer idreservation,Reservation reservation) {
Reservation reservation1 = reservationrepo.findById(idreservation).orElse(null);

reservation1.setCheckinDate(reservation.getCheckinDate());
reservation1.setCheckoutDate(reservation.getCheckoutDate());
reservation1.setTotalPrice(reservation.getTotalPrice());

        return reservationrepo.save(reservation1);
    }

    @Override
    public void removeReservation(Integer idReservation) {
 reservationrepo.deleteById(idReservation);
    }
}
