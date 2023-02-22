package com.example.mobilite_internationale.controllers;

import com.example.mobilite_internationale.entities.Restoration;
import com.example.mobilite_internationale.interfaces.RestorationInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/Restoration")
public class RestorationController {
    @Autowired
    RestorationInterface restorationservice;

    //ajout
    @PostMapping("/add-restoration")
    public Restoration addRestoration(@RequestBody Restoration rst){

        Restoration restoration = restorationservice.addRRestoration(rst);
        return restoration;
    }

    @GetMapping("/retrieve-all-restoration")
    public List<Restoration> retrieveAllRestoration() {
        List<Restoration> restorations = restorationservice.retrieveAllRestoration();
        return restorations;
    }

    @GetMapping("/retrieve-restoration/{id-restoration}")
    public Restoration retrieveRestorationById(@PathVariable("id-restoration") Integer idRestoration) {
        return restorationservice.retrieveRestoration(idRestoration);
    }

    @PutMapping("/update-restoration/{id-restoration}")
    public Restoration updateRestoration(@PathVariable("id-restoration") Integer idRestoration, @RequestBody Restoration rst) {
        Restoration restoration = restorationservice.updateRestoration(idRestoration,rst);
        return restoration;
    }

    @DeleteMapping("/remove-restoration/{id-restoration}")
    public void removeRestoration(@PathVariable("id-restoration") Integer idRestoration) {
        restorationservice.removeRestoration(idRestoration);
    }

}
