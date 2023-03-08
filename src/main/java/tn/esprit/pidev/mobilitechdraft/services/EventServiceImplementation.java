package tn.esprit.pidev.mobilitechdraft.services;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import tn.esprit.pidev.mobilitechdraft.entities.Event;
import tn.esprit.pidev.mobilitechdraft.entities.User;
import tn.esprit.pidev.mobilitechdraft.repositories.EventRepository;

import javax.mail.MessagingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.*;

@Service
public class EventServiceImplementation implements EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserService userService;


    @Autowired
    EmailService emailService;

    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    private static final String APPLICATION_NAME = "Calendar API";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "client_secret.json";
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);

    private List < Event > eventsList;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    @Override
    public Event createEvent(Event event) throws MessagingException, IOException, GeneralSecurityException {
        List<User> userList = new ArrayList<>();
        for (User user : event.getUsers()) {
            Optional<User> user1 = userService.getUserById(user.getIdUser());
            userList.add(user1.get());
        }
        String link = this.newEvent(userList,event);
        event.setEventLinkHangout(link);
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
    public void deleteEvent(Long eventId) {

        eventRepository.deleteById(eventId);
    }

    @Override
    public List<Event> getEventsByUser(User user) {

        return eventRepository.getEventsByUsersOrderByEventDate(user);
    }

    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        InputStream inputStream = new ClassPathResource("credentials.json").getInputStream();
        InputStream inputStreamTokens = new ClassPathResource(TOKENS_DIRECTORY_PATH).getInputStream();

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(inputStreamTokens));
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new File(String.valueOf(inputStream))))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }


    public String newEvent(List<User> attendeeEmails, Event event) throws IOException, GeneralSecurityException {
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
        event2.setSummary(event.getEventTitle());
        event2.setDescription(event.getEventDescription());

        DateTime startDateTime = new DateTime(String.valueOf(event.getEventStartTime()));
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("Europe/Paris");
        event2.setStart(start);

        DateTime endDateTime = new DateTime(String.valueOf(event.getEventEndTime()));
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
        service.events().insert("primary", event2).execute();
        com.google.api.services.calendar.model.Event createdEvent = service.events().insert("primary", event2).setConferenceDataVersion(1).setSendNotifications(true).execute();

        // Print the Google Meet link
        String meetLink = createdEvent.getHangoutLink();

        System.out.printf("Google Meet link: %s\n", meetLink);
        System.out.println(createdEvent);

        // handle the exception
        System.out.println("TEST");
        return meetLink;
    }


    public EventServiceImplementation(List <Event> eventsList) {
        this.eventsList = eventsList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeader() {
        sheet = workbook.createSheet("Events");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "ID", style);
        createCell(row, 1, "Type Event", style);
        createCell(row, 2, "Title", style);
        createCell(row, 3, "Description", style);
        createCell(row, 4, "Date", style);
        createCell(row, 5, "Start Time", style);
        createCell(row, 6, "End Time .", style);
    }

    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else {
            cell.setCellValue((String) valueOfCell);
        }
        cell.setCellStyle(style);
    }

    private void write() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (Event record: eventsList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, record.getEventId(), style);
            createCell(row, columnCount++, record.getEventType().toString(), style);
            createCell(row, columnCount++, record.getEventTitle(), style);
            createCell(row, columnCount++, record.getEventDescription(), style);
            createCell(row, columnCount++, record.getEventDate().toString(), style);
            createCell(row, columnCount++, record.getEventStartTime().toString(), style);
            createCell(row, columnCount++, record.getEventEndTime().toString(), style);
        }
    }

    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
