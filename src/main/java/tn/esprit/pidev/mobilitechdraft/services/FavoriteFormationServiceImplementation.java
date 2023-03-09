package tn.esprit.pidev.mobilitechdraft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.mobilitechdraft.entities.FavoriteFormation;
import tn.esprit.pidev.mobilitechdraft.entities.ReservationFormationStatus;
import tn.esprit.pidev.mobilitechdraft.repositories.FavoriteFormationRepository;

@Service
public class FavoriteFormationServiceImplementation implements FavoriteFormationService{

    @Autowired
    FavoriteFormationRepository favoriteFormationRepository;

    @Override
    public FavoriteFormation createFavoriteFormation(FavoriteFormation favoriteFormation) {

        return favoriteFormationRepository.save(favoriteFormation);
    }

    @Override
    public void deleteFavoriteFormation(Long favoriteFormationId) {

        favoriteFormationRepository.deleteById(favoriteFormationId);
    }
}
