package com.example.mobilite_internationale.repositories;

import com.example.mobilite_internationale.entities.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire,Integer> {
}
