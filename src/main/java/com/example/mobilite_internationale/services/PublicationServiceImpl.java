package com.example.mobilite_internationale.services;

import com.example.mobilite_internationale.entities.Publication;
import com.example.mobilite_internationale.interfaces.PublicationInterface;
import com.example.mobilite_internationale.repositories.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicationServiceImpl implements PublicationInterface {

    @Autowired
    PublicationRepository Publicationrepo;

    @Override
    public Publication addPublication(Publication Publication) {
        return Publicationrepo.save(Publication);
    }

    @Override
    public List<Publication> retrieveAllPublications() {
        return Publicationrepo.findAll();
    }

    @Override
    public Publication retrievePublication(Integer idPublication) {
        return Publicationrepo.findById(idPublication).orElse(null);
    }

    @Override
    public Publication updatePublication(Publication Publication) {


        return Publicationrepo.save(Publication);
    }

    @Override
    public void removePublication(Integer idPublication) {
        Publicationrepo.deleteById(idPublication);
    }
}
