package com.example.mobilite_internationale.services;

import com.example.mobilite_internationale.entities.Accommodation;
import com.example.mobilite_internationale.entities.Reservation;
import com.example.mobilite_internationale.entities.User;
import com.example.mobilite_internationale.interfaces.ReservationInterface;
import com.example.mobilite_internationale.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class    ReservationServiceImpl implements ReservationInterface {

    @Autowired
    ReservationRepository reservationrepo;
    @Autowired
    AccommodationServiceImpl as;


    @Override
    public Boolean addReservation(Reservation reservation, Accommodation accommodation,User user) {

        // si la chambre n'est pas reservée
        if (accommodation.getReservation()==null){
            reservation.setUser(user);
            reservationrepo.save(reservation);
            accommodation.setReservation(reservation);
            // affecter la reservation a l'accomodation ( id reservation dans la table accomodation )
            as.updateAccommodation(accommodation.getIdAccommodation(),accommodation);
            return  true;
        }


        // si les periodes de reservations sont les memes
            if (reservation.getCheckinDate().before(accommodation.getReservation().getCheckoutDate()) && reservation.getCheckoutDate().after(accommodation.getReservation().getCheckinDate())){
                return false;
                }
        //sinon reserve la chambre
        reservation.setUser(user);
        reservationrepo.save(reservation);
        // affecte la reservation a l'accomodation ( id reservation dans la table accomodation )
        as.updateAccommodation(accommodation.getIdAccommodation(),accommodation);

        return true;

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


    public List<Reservation> findReservationsByUser(User user) {
        return reservationrepo.findByUser(user);
    }
    public List<Reservation> findReservationsByUserAndWithinAWeek(User user) {
        // date courante
        Date now = new Date();
        // une semaine
        Calendar oneWeekFromNow = Calendar.getInstance();
        oneWeekFromNow.setTime(now);
        oneWeekFromNow.add(Calendar.DATE, 7);
        return reservationrepo.findByUserAndCheckinDateBetween(user,now, oneWeekFromNow.getTime());
    }
}
