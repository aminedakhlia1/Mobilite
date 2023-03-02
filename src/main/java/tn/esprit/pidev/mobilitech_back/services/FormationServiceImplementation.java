package tn.esprit.pidev.mobilitech_back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.mobilitech_back.entities.Formation;
import tn.esprit.pidev.mobilitech_back.repositories.FormationRepository;

import java.util.List;

@Service
public class FormationServiceImplementation implements FormationService {

    @Autowired
    FormationRepository formationRepository;


    @Override
    public Formation saveFormation(Formation formation) {

        return formationRepository.save(formation);
    }

    @Override
    public List<Formation> getFormations() {

        return formationRepository.findAll();
    }

    /*@Override
    public Formation updateFormation(Long id, Formation formation) {
        formation.setId(id);
        return formationRepository.save(formation);
    }*/

    public Formation updateFormation(Formation f) {

        return formationRepository.save(f);
    }

    @Override
    public void deleteFormation(Long id) {

        formationRepository.deleteById(id);
    }
}
