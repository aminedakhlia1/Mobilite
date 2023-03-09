package com.example.mobilite_internationale.repositories;

import com.example.mobilite_internationale.entities.Reservation;
import com.example.mobilite_internationale.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
    List<Reservation> findByUser(User user);
    List<Reservation> findByUserAndCheckinDateBetween(User user, Date startDate, Date endDate);
}
