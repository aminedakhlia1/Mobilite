package com.example.mobilite_internationale.repositories;

import com.example.mobilite_internationale.entities.Candidacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidacyRepository extends JpaRepository<Candidacy,Integer> {
}
