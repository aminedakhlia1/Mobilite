package com.example.mobilite_internationale.repositories;

import com.example.mobilite_internationale.entities.Commentaire;
import com.example.mobilite_internationale.entities.ReactType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire,Integer> {
    List<Commentaire> findAllByOrderByDateCmntDesc();
    @Query("SELECT c FROM Commentaire c LEFT JOIN c.reactions r WHERE r.type = :reactionType or  r.type is null GROUP BY c.idComment ORDER BY COUNT(r) DESC")
    List<Commentaire> findAllByMostReacted(@Param("reactionType") ReactType reactionType);


}
