package tn.esprit.pidev.mobilitech_back.services;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarRequestInitializer;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import tn.esprit.pidev.mobilitech_back.entities.User;
import tn.esprit.pidev.mobilitech_back.entities.Event;
import tn.esprit.pidev.mobilitech_back.repositories.UserRepository;
import tn.esprit.pidev.mobilitech_back.repositories.EventRepository;

import java.io.*;


import java.security.GeneralSecurityException;
import java.util.*;


@Service
public class EventServiceImplementation implements EventService {
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    private static final String APPLICATION_NAME = "Calendar API";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "client_secret.json";
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);



    @Autowired
    private EventRepository eventRepository;

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;


    public Event createEvent(Event event) throws MessagingException, IOException, GeneralSecurityException {
        List<User> userList = new ArrayList<>();
        for (User user : event.getUsers()) {
            Optional<User> user1 = userService.getUserById(user.getId());
            userList.add(user1.get());
        }
        String link = this.newEvent(userList,event);
        event.setLinkHangout(link);
        for (User e : userList) {
            emailService.sendEmail(event, e);
        }

        return eventRepository.save(event);
    }

    @Override
    public Optional<Event> getEvent(Long eventId) {

        return eventRepository.findById(eventId);
    }

    @Override
    public List<Event> getAllEvent() {

        return eventRepository.findAll();
    }

    @Override
    public Event updateEvent(Event event) {

        return eventRepository.save(event);
    }

    @Override
    public void deleteEvent(String eventId) {

        eventRepository.deleteById(Long.valueOf(eventId));
    }

    @Override
    public List<Event> getEventsByUser(User user) {

        return eventRepository.getEventsByUsersOrderByDate(user);
    }

    @Override
    public List<User> getUsersByEvent(Event event) {

        return null;
    }


    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        InputStream inputStream = new ClassPathResource("credentials.json").getInputStream();
        InputStream inputStreamTokens = new ClassPathResource(TOKENS_DIRECTORY_PATH).getInputStream();

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(inputStreamTokens));
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(String.valueOf(inputStream))))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public String newEvent(List<User> attendeeEmails,Event event) throws IOException, GeneralSecurityException {

        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();


        Credential credential = getCredentials(httpTransport);
        Calendar service = new Calendar.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();

        // Create a new event with conference data
        List<EventAttendee> attendees=new ArrayList<>();
        com.google.api.services.calendar.model.Event.Organizer organizer =new com.google.api.services.calendar.model.Event.Organizer();
        com.google.api.services.calendar.model.Event event2 = new com.google.api.services.calendar.model.Event();
        organizer.setEmail("yosr.dahmani@gmail.com");
        event2.setOrganizer(organizer);
        event2.setSummary(event.getTitle());
        event2.setDescription(event.getDescription());

        DateTime startDateTime = new DateTime(String.valueOf(event.getStartTime()));
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("Europe/Paris");
        event2.setStart(start);

        DateTime endDateTime = new DateTime(String.valueOf(event.getEndTime()));
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("Europe/Paris");
        event2.setEnd(end);


        for(User userAttendee:attendeeEmails){
            EventAttendee attendee = new EventAttendee();
            attendee.setEmail(userAttendee.getEmail());
            attendees.add(attendee);
        }

        event2.setAttendees(attendees);
        ConferenceSolutionKey conferenceSolutionKey = new ConferenceSolutionKey()
                .setType("hangoutsMeet");
        CreateConferenceRequest createConferenceRequest = new CreateConferenceRequest()
                .setRequestId(UUID.randomUUID().toString())
                .setConferenceSolutionKey(conferenceSolutionKey);
        ConferenceData conferenceData = new ConferenceData()
                .setCreateRequest(createConferenceRequest);
        EventReminder c =new EventReminder();
        c.setMinutes(10);

        event2.setConferenceData(conferenceData);
        com.google.api.services.calendar.model.Event createdEvent = service.events().insert("primary", event2).setConferenceDataVersion(1).setSendNotifications(true).execute();
// Print the Google Meet link

        String meetLink = createdEvent.getHangoutLink();

        System.out.printf("Google Meet link: %s\n", meetLink);
        System.out.println(createdEvent);


        // handle the exception
        System.out.println("TEST");
        return meetLink;
    }
}
