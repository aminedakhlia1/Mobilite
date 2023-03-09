package com.example.mobilite_internationale.controllers;

import com.example.mobilite_internationale.entities.Candidacy;
import com.example.mobilite_internationale.interfaces.MobiliteInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("")
public class CandidacyController {

    @Autowired
    MobiliteInterface mobiliteInterface;

    @PostMapping(value = "/add-candidacy-file-and-assign-to-opportunity-and-user/{idOpportunity}/{idUser}", consumes = {"multipart/form-data"})
    public ResponseEntity<Candidacy> addCandidacyWithFile(@PathVariable("idOpportunity") Integer idOpportunity,
                                                          @PathVariable("idUser") Integer idUser,
                                                          @Valid @RequestParam("candidacy") String candidacyJson,
                                                          @RequestParam("file") MultipartFile file ) {

        //Convertir JSON string un objet
        ObjectMapper objectMapper = new ObjectMapper();
        Candidacy candidacy = null;
        try {
            candidacy = objectMapper.readValue(candidacyJson, Candidacy.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Candidacy savedCandidacy = mobiliteInterface.addCandidacyWithFileAndAssignToOpportunityAndUser(candidacy, idOpportunity, file, idUser);
        return new ResponseEntity<>(savedCandidacy, HttpStatus.CREATED);
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
}
