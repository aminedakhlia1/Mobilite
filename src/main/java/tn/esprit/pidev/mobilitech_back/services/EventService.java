package tn.esprit.pidev.mobilitech_back.services;

import jakarta.mail.MessagingException;
import tn.esprit.pidev.mobilitech_back.entities.Event;
import tn.esprit.pidev.mobilitech_back.entities.User;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;

public interface EventService {
    Event createEvent(Event event) throws MessagingException, IOException, GeneralSecurityException;

    Optional<Event> getEvent(Long eventId);

    List<Event> getAllEvent();

    Event updateEvent(Event event);

    void deleteEvent(String eventId);

    List<Event> getEventsByUser(User user);

    List<User> getUsersByEvent(Event event);
}
