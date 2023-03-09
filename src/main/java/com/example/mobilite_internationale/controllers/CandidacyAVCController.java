package com.example.mobilite_internationale.controllers;

import com.example.mobilite_internationale.dto.CandidacyDTO;
import com.example.mobilite_internationale.entities.Candidacy;
import com.example.mobilite_internationale.interfaces.MobiliteInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class CandidacyAVCController {

    @Autowired
    MobiliteInterface mobiliteInterface;



    @GetMapping("/find-candicacies-by-opportunity/{id-opportunity}")
    public List<Candidacy> findCandidaciesByOpportunity(@PathVariable("id-opportunity") Integer idOpportunity) {
        List<Candidacy> candidacies = mobiliteInterface.findCandidaciesByOpportunity(idOpportunity);
        return candidacies;
    }


    @GetMapping("/count-candidacies-by-opportunities/{id-opportunity}")
    public Integer countCandidaciesByOpportunityId(@PathVariable("id-opportunity") Integer idOpportunity) {
        return mobiliteInterface.countCandidaciesByOpportunity(idOpportunity);
    }

    @GetMapping("/calculateCandidacyScoreByOpportunity/{id-opportunity}/{id-candidacy}")
    public ResponseEntity<Float> calculateCandidacyScoreByOpportunity(@PathVariable("id-opportunity") Integer idOpportunity,
                                                                      @PathVariable("id-candidacy") Integer idCandidacy) {
        float score = mobiliteInterface.calculateCandidacyScoreByOpportunity(idOpportunity, idCandidacy);
        return ResponseEntity.ok(score);
    }

    @PutMapping("/update-candidacy-status_By_Opportunity/{id-opportunity}")
    public void updateCandidacyStatus_ByOpportunity(@PathVariable("id-opportunity") Integer idOpportunity) {
        mobiliteInterface.updateCandidacyStatus_ByOpportunity(idOpportunity);
    }

    @GetMapping("/get-candidacy-history/{id-user}")
    public List<CandidacyDTO> getCandidacyHistory(@PathVariable("id-user") Integer idUser) {
        return mobiliteInterface.getCandidacyHistoryForStudent(idUser);
    }

}
