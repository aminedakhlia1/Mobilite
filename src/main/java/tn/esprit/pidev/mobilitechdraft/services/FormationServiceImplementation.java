package tn.esprit.pidev.mobilitechdraft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.mobilitechdraft.entities.Formation;
import tn.esprit.pidev.mobilitechdraft.repositories.FormationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FormationServiceImplementation implements FormationService{

    @Autowired
    FormationRepository formationRepository;

    @Override
    public Formation createFormation(Formation formation) {

        return formationRepository.save(formation);
    }

    @Override
    public Optional<Formation> getFormation(Long formationId) {

        return formationRepository.findById(formationId);
    }

    @Override
    public List<Formation> getAllFormation() {

        return formationRepository.findAll();
    }

    @Override
    public Formation updateFormation(Formation formation) {

        return formationRepository.save(formation);
    }

    @Override
    public void deleteFormation(Long formationId) {

        formationRepository.deleteById(formationId);
    }
}
