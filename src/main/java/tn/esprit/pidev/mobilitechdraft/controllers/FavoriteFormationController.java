package tn.esprit.pidev.mobilitechdraft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.mobilitechdraft.entities.FavoriteFormation;
import tn.esprit.pidev.mobilitechdraft.services.FavoriteFormationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/favoriteFormations")
public class FavoriteFormationController {

    @Autowired
    FavoriteFormationService favoriteFormationService;

    @RequestMapping(value = "/create-favorite-formation", method = RequestMethod.POST)
    public ResponseEntity<String> createFavoriteFormation(@Valid @RequestBody FavoriteFormation favoriteFormation) {
        favoriteFormationService.createFavoriteFormation(favoriteFormation);
        return ResponseEntity.ok("formation added to the favorite list");
    }


    @DeleteMapping("/delete-favorite-formation/{favoriteFormationId}")
    public void deleteFavoriteFormation(@PathVariable("favoriteFormationId") Long favoriteFormationId) {

        favoriteFormationService.deleteFavoriteFormation(favoriteFormationId);
    }
}
