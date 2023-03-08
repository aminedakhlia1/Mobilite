package tn.esprit.pidev.mobilitechdraft.services;

import tn.esprit.pidev.mobilitechdraft.entities.Event;
import tn.esprit.pidev.mobilitechdraft.entities.User;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;

public interface EventService {

    Event createEvent(Event event) throws MessagingException, IOException, GeneralSecurityException;

    Optional<Event> getEvent(Long eventId);

    List<Event> getAllEvent();

    Event updateEvent(Event event);

    void deleteEvent(Long eventId);

    List<Event> getEventsByUser(User user);

}
