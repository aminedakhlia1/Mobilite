package com.example.mobilite_internationale.interfaces;

import com.example.mobilite_internationale.entities.Accommodation;
import com.example.mobilite_internationale.entities.Opportunity;
import com.example.mobilite_internationale.entities.Reservation;
import com.example.mobilite_internationale.entities.User;

import java.util.List;

public interface ReservationInterface {
    public Boolean addReservation (Reservation reservation, Accommodation accommodation,User user);
    public List<Reservation> retrieveAllReservation();
    public Reservation retrieveReservation (Integer  idReservation);
    public Reservation updateReservation (Integer id,Reservation reservation);
    public void removeReservation(Integer idReservation);
    public List<Reservation> findReservationsByUser(User user);
    public List<Reservation> findReservationsByUserAndWithinAWeek(User user);
}
