package com.example.mobilite_internationale.repositories;

import com.example.mobilite_internationale.entities.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpportunityRepository extends JpaRepository<Opportunity,Integer> {
}
