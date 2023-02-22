package com.example.mobilite_internationale.services;

import com.example.mobilite_internationale.entities.Reservation;
import com.example.mobilite_internationale.entities.Restoration;
import com.example.mobilite_internationale.interfaces.RestorationInterface;
import com.example.mobilite_internationale.repositories.RestorationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestorationServiceImpl implements RestorationInterface {

    @Autowired
    RestorationRepository restorationrepository;
    @Override
    public Restoration addRRestoration(Restoration restoration) {
        return restorationrepository.save(restoration);
    }

    @Override
    public List<Restoration> retrieveAllRestoration() {
        return restorationrepository.findAll();
    }

    @Override
    public Restoration retrieveRestoration(Integer idRestoration) {
        return restorationrepository.findById(idRestoration).orElse(null);
    }

    @Override
    public Restoration updateRestoration(Integer id, Restoration restoration) {
        Restoration restoration1 = restorationrepository.findById(id).orElse(null);

        restoration1.setTypeResotration(restoration.getTypeResotration());

        return restorationrepository.save(restoration1);
    }

    @Override
    public void removeRestoration(Integer idRestoration) {
        restorationrepository.deleteById(idRestoration);
    }
}
