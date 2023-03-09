package com.example.mobilite_internationale.controllers;

import com.example.mobilite_internationale.entities.Opportunity;
import com.example.mobilite_internationale.entities.Speciality;
import com.example.mobilite_internationale.entities.TypeOpportunity;
import com.example.mobilite_internationale.interfaces.MobiliteInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class OpportunityController {

    @Autowired
    MobiliteInterface mobiliteInterface;

    @PostMapping("addOpportunityAndAssignToUser/{id-user}")
    public ResponseEntity<Opportunity> addOpportunityAndAssignToUser(@RequestBody Opportunity opportunity,
                                                                     @PathVariable("id-user") Integer idUser) {
        Opportunity opportunity1 = mobiliteInterface.addOpportunityAndAssignToUser(opportunity, idUser);
        return ResponseEntity.ok(opportunity1);
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

    @PutMapping("/update-opportunity/{id-user}")
    public Opportunity updateOpportunity(@RequestBody Opportunity opp,
                                         @PathVariable("id-user") Integer idUser) {
        Opportunity opportunity = mobiliteInterface.updateOpportunity(opp,idUser);
        return opportunity;
    }

    @DeleteMapping("/remove-opportunity/{id-opportunity}")
    public void removeContrat(@PathVariable("id-opportunity") Integer idOpportunity) {
        mobiliteInterface.removeOpportunity(idOpportunity);
    }


}
