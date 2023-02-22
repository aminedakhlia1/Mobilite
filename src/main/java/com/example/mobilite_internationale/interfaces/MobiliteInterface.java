package com.example.mobilite_internationale.interfaces;

import com.example.mobilite_internationale.entities.Candidacy;
import com.example.mobilite_internationale.entities.File;
import com.example.mobilite_internationale.entities.Opportunity;

import java.util.List;

public interface MobiliteInterface {

    /*-------------- Opportunity --------------*/
    public Opportunity addOpportunity (Opportunity opportunity);
    public List<Opportunity> retrieveAllOpportunites();
    public Opportunity retrieveOpportunity (Integer  idOpportunity);
    public Opportunity updateOpportunity (Opportunity opportunity);
    public void removeOpportunity(Integer idOpportunity);

    /*-------------- Candidacy --------------*/
    public Candidacy addCandidacy (Candidacy candidacy);
    public List<Candidacy> retrieveAllCandidacies();
    public Candidacy retrieveCandidacy (Integer  idCandidacy);
    public Candidacy updateCandidacy (Candidacy candidacy);
    public void removeCandidacy(Integer idCandidacy);

    /*-------------- File --------------*/
    public File addFile (File file);
    public List<File> retrieveAllFiles();


}
