package com.example.mobilite_internationale.interfaces;

import com.example.mobilite_internationale.entities.Accommodation;
import com.example.mobilite_internationale.entities.Reservation;

import java.util.List;

public interface AccommodationInterface {
    public Accommodation addAccommodation (Accommodation accommodation);
    public List<Accommodation> retrieveAllAccommodation();
    public Accommodation retrieveAccommodation (Integer  idAccommodation);
    public Accommodation updateAccommodation (Integer id,Accommodation accommodation);
    public void removeAccommodation(Integer idAccommodation);

    public List<Accommodation> AccommodationReccomondation(Accommodation a);
}
