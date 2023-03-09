package com.example.mobilite_internationale.controllers;

import com.example.mobilite_internationale.entities.Opportunity;
import com.example.mobilite_internationale.entities.Speciality;
import com.example.mobilite_internationale.entities.TypeOpportunity;
import com.example.mobilite_internationale.interfaces.MobiliteInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
public class SearchAndSortOpportunityController {

    @Autowired
    MobiliteInterface mobiliteInterface;

    @GetMapping("search-opportunity-by-speciality/{speciality}")
    public ResponseEntity<List<Opportunity>> searchOpportunityBySpeciality(@PathVariable String speciality) {
        Speciality spec = Speciality.valueOf(speciality);
        List<Opportunity> opportunities = mobiliteInterface.searchOpportunityBySpeciality(spec);
        return ResponseEntity.ok().body(opportunities);
    }

    @GetMapping("search-opportunity-by-type/{typeOpportunity}")
    public ResponseEntity<List<Opportunity>> searchOpportunityByType(@PathVariable String typeOpportunity) {
        TypeOpportunity typeOpp = TypeOpportunity.valueOf(typeOpportunity);
        List<Opportunity> opportunities = mobiliteInterface.searchOpportunityByType(typeOpp);
        return ResponseEntity.ok().body(opportunities);
    }

    @GetMapping("sort-opportunities-by-startDate-Desc")
    public ResponseEntity<List<Opportunity>> sortOpportunitiesByStartDateDesc() {
        List<Opportunity> opportunities = mobiliteInterface.sortOpportunitiesByStartDateDesc();
        return ResponseEntity.ok().body(opportunities);
    }

    @GetMapping("get-available-opportunities")
    public ResponseEntity<List<Opportunity>> getAvailableOpportunities() {
        List<Opportunity> availableOpportunities = mobiliteInterface.getAvailableOpportunities();
        return ResponseEntity.ok().body(availableOpportunities);
    }
}

