package com.example.mobilite_internationale.controllers;

import com.example.mobilite_internationale.entities.Candidacy;
import com.example.mobilite_internationale.entities.File;
import com.example.mobilite_internationale.entities.Opportunity;
import com.example.mobilite_internationale.entities.User;
import com.example.mobilite_internationale.interfaces.MobiliteInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Mobilite")
public class MobiliteController {

    @Autowired
    MobiliteInterface mobiliteInterface;



    /*-------------- Opportunity --------------*/
    @PostMapping("/add-opportunity")
    public Opportunity addOpportunity(@RequestBody Opportunity opp) {
        Opportunity opportunity = mobiliteInterface.addOpportunity(opp);
        return opportunity;
    }

    @GetMapping("/retrieve-all-opportunities")
    public List<Opportunity> retrieveAllOpportunites() {
        List<Opportunity> opportunities = mobiliteInterface.retrieveAllOpportunites();
        return opportunities;
    }

    @GetMapping("/retrieve-opportunity/{id-opportunity}")
    public Opportunity retrieveOpportunity(@PathVariable("id-opportunity") Integer idOpportunity) {
        return mobiliteInterface.retrieveOpportunity(idOpportunity);
    }

    @PutMapping("/update-opportunity")
    public Opportunity updateOpportunity(@RequestBody Opportunity opp) {
        Opportunity opportunity = mobiliteInterface.updateOpportunity(opp);
        return opportunity;
    }

    @DeleteMapping("/remove-opportunity/{id-opportunity}")
    public void removeContrat(@PathVariable("id-opportunity") Integer idOpportunity) {
        mobiliteInterface.removeOpportunity(idOpportunity);
    }


    /*-------------- Candidacy --------------*/
    @PostMapping("/add-candidacy")
    public Candidacy addCandidacy(@RequestBody Candidacy c) {
        Candidacy candidacy = mobiliteInterface.addCandidacy(c);
        return candidacy;
    }

    @GetMapping("/retrieve-all-candidacies")
    public List<Candidacy> retrieveAllCandidacies() {
        List<Candidacy> candidacies = mobiliteInterface.retrieveAllCandidacies();
        return candidacies;
    }

    @GetMapping("/retrieve-candidacy/{id-candidacy}")
    public Candidacy retrieveCandidacy(@PathVariable("id-candidacy") Integer idCandidacy) {
        return mobiliteInterface.retrieveCandidacy(idCandidacy);
    }

    @PutMapping("/update-candidacy")
    public Candidacy updateCandidacy(@RequestBody Candidacy c) {
        Candidacy candidacy = mobiliteInterface.updateCandidacy(c);
        return candidacy;
    }

    @DeleteMapping("/remove-candidacy/{id-candidacy}")
    public void removeCandidacy(@PathVariable("id-candidacy") Integer idCandidacy) {
        mobiliteInterface.removeCandidacy(idCandidacy);
    }

    /*-------------- File --------------*/
    @PostMapping("/add-file")
    public File addFile(@RequestBody File f) {
        File file = mobiliteInterface.addFile(f);
        return file;
    }

    @GetMapping("/retrieve-all-files")
    public List<File> retrieveAllFiles() {
        List<File> files = mobiliteInterface.retrieveAllFiles();
        return files;
    }

}
