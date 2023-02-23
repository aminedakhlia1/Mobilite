package com.example.mobilite_internationale.controllers;

import com.example.mobilite_internationale.entities.Publication;
import com.example.mobilite_internationale.interfaces.PublicationInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Publication")
public class PublicationController {
    @Autowired
    PublicationInterface PublicationService;

    //ajout
    @PostMapping("/add-Publication")
    public Publication addPublication(@RequestBody Publication rsv){
        //return PublicationService.addPublication(rsv);
        Publication Publication= PublicationService.addPublication(rsv);
        return  Publication;
    }

    @GetMapping("/retrieve-all-Publication")
    public List<Publication> retrieveAllResevations() {
        List<Publication> Publications = PublicationService.retrieveAllPublications();
        return Publications;
    }

    @GetMapping("/retrieve-Publication/{id-Publication}")
    public Publication retrievePublicationById(@PathVariable("id-Publication") Integer idPublication) {
        return PublicationService.retrievePublication(idPublication);
    }

    @PutMapping("/update-Publication")
    public Publication updatePublication(@RequestBody Publication pub) {
        Publication Publication = PublicationService.updatePublication(pub);
        return Publication;
    }

    @DeleteMapping("/remove-Publication/{id-Publication}")
    public void removePublication(@PathVariable("id-Publication") Integer idPublication) {
        PublicationService.removePublication(idPublication);
    }

}
