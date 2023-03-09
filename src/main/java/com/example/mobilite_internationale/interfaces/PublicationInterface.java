package com.example.mobilite_internationale.interfaces;

import com.example.mobilite_internationale.entities.Publication;

import java.util.List;

public interface PublicationInterface {
    public Publication addPublication (Publication Publication);
    public List<Publication> retrieveAllPublications();
    public Publication retrievePublication (Integer  idPublication);
    public Publication updatePublication (Publication Publication);
    public void removePublication(Integer idPublication);
}
