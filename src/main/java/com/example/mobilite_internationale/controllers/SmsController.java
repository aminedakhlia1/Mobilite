package com.example.mobilite_internationale.controllers;
import com.example.mobilite_internationale.entities.Reservation;
import com.example.mobilite_internationale.entities.User;
import com.example.mobilite_internationale.interfaces.ReservationInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
public class SmsController {
    @Autowired
    ReservationInterface reservationService;

    @GetMapping(value = "/sendSMS/{userId}")
    public ResponseEntity<String> sendSMS(@PathVariable Long userId) {
        User user = new User(userId);
        List<Reservation> reservations = reservationService.findReservationsByUserAndWithinAWeek(user);
        if (reservations.isEmpty()) {
            return null;
        }
        String msg = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        for (Reservation reservation : reservations) {
            System.out.println("xxxxxxx"+reservation.getUser().getUsername());
            msg = "Hello " + reservation.getUser().getUsername() + " You have a reservation on "
                    +dateFormat.format(reservation.getCheckinDate())+ " for the room number  : "
                    + reservation.getAccommodation().getIdAccommodation();
            System.out.println(msg);

            break;
        }

        Twilio.init(System.getenv("TWILIO_ACCOUNT_SID"), System.getenv("TWILIO_AUTH_TOKEN"));


        Message.creator(new PhoneNumber("+216 58532921"),
                new PhoneNumber("+15674234287"), "📞"+msg).create();
        return new ResponseEntity<String>("Message sent successfully\n"+msg , HttpStatus.OK);

    }
}