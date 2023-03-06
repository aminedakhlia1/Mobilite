package com.example.mobilite_internationale.repositories;

import com.example.mobilite_internationale.entities.Candidacy;
import com.example.mobilite_internationale.entities.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidacyRepository extends JpaRepository<Candidacy,Integer> {

    @Query("SELECT COUNT(c) FROM Candidacy c WHERE c.opportunity.idOpportunity = :idOpportunity")
    int countCandidaciesByOpportunity(@Param("idOpportunity") Integer idOpportunity);

    List<Candidacy> findByOpportunityIdOpportunity(Integer idOpportunity);

    List<Candidacy> findByOpportunityOrderByScoreDesc(Opportunity opportunity);
}
