package tn.esprit.pidev.mobilitechdraft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.mobilitechdraft.entities.FavoriteFormation;

@Repository
public interface FavoriteFormationRepository extends JpaRepository<FavoriteFormation, Long> {

    FavoriteFormation findReservationFormationByFavoriteFormationId(Long reservationFormationId);
}
