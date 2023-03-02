package tn.esprit.pidev.mobilitech_back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tn.esprit.pidev.mobilitech_back.entities.Formation;

@RepositoryRestResource
public interface FormationRepository extends JpaRepository<Formation,Long> {
}
