package com.example.mobilite_internationale.repositories;

import com.example.mobilite_internationale.entities.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationRepository extends JpaRepository<Publication,Integer> {
}
