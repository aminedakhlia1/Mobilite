package com.example.mobilite_internationale.interfaces;

import com.example.mobilite_internationale.entities.Opportunity;
import com.example.mobilite_internationale.entities.Reservation;

import java.util.List;

public interface ReservationInterface {
    public Reservation addReservation (Reservation reservation);
    public List<Reservation> retrieveAllReservation();
    public Reservation retrieveReservation (Integer  idReservation);
    public Reservation updateReservation (Integer id,Reservation reservation);
    public void removeReservation(Integer idReservation);
}
