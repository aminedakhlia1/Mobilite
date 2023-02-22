package com.example.mobilite_internationale.controllers;

import com.example.mobilite_internationale.entities.Accommodation;
import com.example.mobilite_internationale.entities.Reservation;
import com.example.mobilite_internationale.interfaces.AccommodationInterface;
import com.example.mobilite_internationale.interfaces.ReservationInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/Accommodation")
public class AccommodationController {
    @Autowired
    AccommodationInterface accommodationservice;

    //ajout
    @PostMapping("/add-accommodation")
    public Accommodation addAccommodation(@RequestBody Accommodation acmd){

        Accommodation accommodation= accommodationservice.addAccommodation(acmd);
        return  accommodation;
    }

    @GetMapping("/retrieve-all-accommodation")
    public List<Accommodation> retrieveAllAccommodation() {
        List<Accommodation> Accommodations = accommodationservice.retrieveAllAccommodation();
        return Accommodations;
    }

    @GetMapping("/retrieve-accommodation/{id-accommodation}")
    public Accommodation retrieveAccommodationById(@PathVariable("id-accommodation") Integer idAccommodation) {
        return accommodationservice.retrieveAccommodation(idAccommodation);
    }

    @PutMapping("/update-accommodation/{id-accommodation}")
    public Accommodation updateAccommodation(@PathVariable("id-accommodation") Integer idAccommodation,@RequestBody Accommodation acmd) {
        Accommodation accommodation = accommodationservice.updateAccommodation(idAccommodation,acmd);
        return accommodation;
    }

    @DeleteMapping("/remove-accommodation/{id-accommodation}")
    public void removeAccommodation(@PathVariable("id-accommodation") Integer idaccommodation) {
        accommodationservice.removeAccommodation(idaccommodation);
    }

}
