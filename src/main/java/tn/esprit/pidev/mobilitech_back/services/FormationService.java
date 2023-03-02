package tn.esprit.pidev.mobilitech_back.services;

import tn.esprit.pidev.mobilitech_back.entities.Formation;

import java.util.List;

public interface FormationService {

    Formation saveFormation(Formation formation);
    List<Formation> getFormations();

    Formation updateFormation (Formation  f);

    //Formation updateFormation(Long id, Formation formation);
    void deleteFormation(Long id);
}