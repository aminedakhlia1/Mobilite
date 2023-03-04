package com.example.mobilite_internationale.repositories;

import com.example.mobilite_internationale.entities.Opportunity;
import com.example.mobilite_internationale.entities.Speciality;
import com.example.mobilite_internationale.entities.TypeOpportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface OpportunityRepository extends JpaRepository<Opportunity,Integer> {

    List<Opportunity> findBySpeciality(Speciality speciality);
    List<Opportunity> findByTypeOpportunity(TypeOpportunity typeOpportunity);
    List<Opportunity> findByEndDateGreaterThanEqual(LocalDate currentDate);
    //List<Opportunity> findAllByStartDateDesc();
    @Query("SELECT o FROM Opportunity o ORDER BY o.startDate DESC") List<Opportunity> findAllByStartDateDesc();
}
