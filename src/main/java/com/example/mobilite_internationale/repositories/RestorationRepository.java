package com.example.mobilite_internationale.repositories;

import com.example.mobilite_internationale.entities.Restoration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestorationRepository extends JpaRepository<Restoration,Integer>{
}
