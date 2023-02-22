package com.example.mobilite_internationale.services;

import com.example.mobilite_internationale.entities.Accommodation;
import com.example.mobilite_internationale.entities.Reservation;
import com.example.mobilite_internationale.interfaces.AccommodationInterface;
import com.example.mobilite_internationale.repositories.AccommodationRepository;
import com.example.mobilite_internationale.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccommodationServiceImpl implements AccommodationInterface {
    @Autowired
    AccommodationRepository accomodationRepo;


    @Override
    public Accommodation addAccommodation(Accommodation accommodation) {
        return accomodationRepo.save(accommodation);
    }

    @Override
    public List<Accommodation> retrieveAllAccommodation() {
        return  accomodationRepo.findAll();
    }

    @Override
    public Accommodation retrieveAccommodation(Integer idAccommodation) {
        return accomodationRepo.findById(idAccommodation).orElse(null);
    }

    @Override
    public Accommodation updateAccommodation(Integer id, Accommodation accommodation) {
        Accommodation accommodation1 = accomodationRepo.findById(id).orElse(null);

        accommodation1.setNameAccommodation(accommodation.getNameAccommodation());
        accommodation1.setTypeAccommodation(accommodation.getTypeAccommodation());
        accommodation1.setCapacity(accommodation.getCapacity());

        return accomodationRepo.save(accommodation1);
    }

    @Override
    public void removeAccommodation(Integer idAccommodation) {
    accomodationRepo.deleteById(idAccommodation);
    }
}
