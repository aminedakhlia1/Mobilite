package tn.esprit.pidev.mobilitechdraft.services;

import tn.esprit.pidev.mobilitechdraft.entities.Event;
import tn.esprit.pidev.mobilitechdraft.entities.Formation;

import java.util.List;
import java.util.Optional;

public interface FormationService {

    Formation createFormation(Formation formation);

    Optional<Formation> getFormation(Long formationId);

    List<Formation> getAllFormation();

    Formation updateFormation (Formation formation);

    void deleteFormation(Long formationId);
}
