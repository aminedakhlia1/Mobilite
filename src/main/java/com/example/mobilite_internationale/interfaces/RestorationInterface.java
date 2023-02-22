package com.example.mobilite_internationale.interfaces;

import com.example.mobilite_internationale.entities.Restoration;

import java.util.List;

public interface RestorationInterface {
    public Restoration addRRestoration (Restoration restoration);
    public List<Restoration> retrieveAllRestoration();
    public Restoration retrieveRestoration (Integer  idRestoration);
    public Restoration updateRestoration (Integer id, Restoration restoration);
    public void removeRestoration(Integer idRestoration);
}
