package tn.esprit.pidev.mobilitech_back.controllers;

import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.mobilitech_back.entities.Event;
import tn.esprit.pidev.mobilitech_back.entities.User;
import tn.esprit.pidev.mobilitech_back.services.EmailService;
import tn.esprit.pidev.mobilitech_back.services.EventServiceImplementation;
import tn.esprit.pidev.mobilitech_back.services.UserService;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventServiceImplementation eventService;
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;


    @PostMapping("/create")
    public Event createEvent(@RequestBody Event event) throws MessagingException, IOException, GeneralSecurityException {
        return eventService.createEvent(event);
    }

    @GetMapping("/getAll")
    public List<Event> getAllEvent() {

        return eventService.getAllEvent();
    }

    @GetMapping("/get/{eventId}")
    public Optional<Event> getEvent(@PathVariable("eventId") Long eventId) {

        return eventService.getEvent(eventId);
    }

    @PutMapping("/update/{eventId}")
    public Event updateEvent(@PathVariable("eventId") Long eventId, @RequestBody Event event) {
        event.setId(eventId);
        return eventService.updateEvent(event);
    }

    @DeleteMapping("/delete/{eventId}")
    public void deleteEvent(@PathVariable("eventId") String eventId) {

        eventService.deleteEvent(eventId);
    }

    @GetMapping("/filterByEvent/{idUser}")
    public List<Event> getEventsByUser(@PathVariable("idUser") Long id){
        Optional<User> user = userService.getUserById(id);
        return eventService.getEventsByUser(user.get());
    }
}