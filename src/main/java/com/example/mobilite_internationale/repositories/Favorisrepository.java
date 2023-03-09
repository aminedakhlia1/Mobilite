package com.example.mobilite_internationale.repositories;

import com.example.mobilite_internationale.entities.Commentaire;
import com.example.mobilite_internationale.entities.Favoris;
import com.example.mobilite_internationale.entities.Publication;
import com.example.mobilite_internationale.entities.ReactType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Favorisrepository extends JpaRepository<Favoris,Integer> {
    @Query("SELECT F FROM Favoris F  WHERE F.Publication.idPub=:idPub")
    Favoris getFavorisByPub(Integer idPub);

}
