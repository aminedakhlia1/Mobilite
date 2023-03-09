package tn.esprit.pidev.mobilitechdraft.services;

import tn.esprit.pidev.mobilitechdraft.entities.FavoriteFormation;

public interface FavoriteFormationService {

    FavoriteFormation createFavoriteFormation(FavoriteFormation favoriteFormation);

    void deleteFavoriteFormation(Long FavoriteFormationId);
}
