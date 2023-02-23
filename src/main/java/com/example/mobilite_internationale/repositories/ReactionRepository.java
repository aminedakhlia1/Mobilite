package com.example.mobilite_internationale.repositories;

import com.example.mobilite_internationale.entities.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction,Integer> {
}
