package tn.esprit.pidev.mobilitechdraft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.mobilitechdraft.entities.Event;
import tn.esprit.pidev.mobilitechdraft.entities.User;
import tn.esprit.pidev.mobilitechdraft.services.EventService;
import tn.esprit.pidev.mobilitechdraft.services.EventServiceImplementation;
import tn.esprit.pidev.mobilitechdraft.services.UserService;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;


    @PostMapping("/create-event")
    public Event createEvent(@RequestBody Event event) throws MessagingException, IOException, GeneralSecurityException {
        return eventService.createEvent(event);
    }


    @GetMapping("/get-event/{eventId}")
    public Optional<Event> getEvent(@PathVariable("eventId") Long eventId) {

        return eventService.getEvent(eventId);
    }


    @GetMapping("/get-all-event")
    public List<Event> getAllEvent() {

        return eventService.getAllEvent();
    }

    @PutMapping("/update-event/{eventId}")
    public Event updateEvent(@PathVariable("eventId") Long eventId, @RequestBody Event event) {

        event.setEventId(eventId);
        return eventService.updateEvent(event);
    }

    @DeleteMapping("/delete-event/{eventId}")
    public void deleteEvent(@PathVariable("eventId") Long eventId) {

        eventService.deleteEvent(eventId);
    }


    @GetMapping("/get-event-by-user/{idUser}")
    public List<Event> getEventsByUser(@PathVariable("userId") Integer idUser){
        Optional<User> user = userService.getUserById(idUser);
        return eventService.getEventsByUser(user.get());
    }


    @GetMapping("/generateExcel")
    public void exportIntoExcelFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=student" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        ArrayList<Event> listOfStudents = (ArrayList<Event>) eventService.getAllEvent();
        EventServiceImplementation generator = new EventServiceImplementation(listOfStudents);
        generator.generateExcelFile(response);
    }
}