package com.example.mobilite_internationale.repositories;

import com.example.mobilite_internationale.entities.Accommodation;
import com.example.mobilite_internationale.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation,Integer> {
    @Query("SELECT A FROM Accommodation A  WHERE A.Capacity =:capacite AND A.TypeAccommodation=:Type And A.reservation is null")
    List<Accommodation> AccomodationReccomondation(String capacite,String Type);
}
