package com.example.mobilite_internationale.controllers;

import com.example.mobilite_internationale.interfaces.MobiliteInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@RequestMapping("")
public class MailingController {

    @Autowired
    MobiliteInterface mobiliteInterface;

    @PostMapping("/send-email-for-new-opportunity/{id-opportunity}")
    public void sendEmailsForNewOpportunities(@PathVariable("id-opportunity") Integer idOpportunity) throws MessagingException, IOException {
        mobiliteInterface.sendEmailsForNewOpportunities(idOpportunity);
    }

    @PostMapping("/send-email-to-candidacies/{id-opportunity}")
    public void sendEmailsToCandidacy(@PathVariable("id-opportunity") Integer idOpportunity) throws MessagingException, IOException {
        mobiliteInterface.sendEmailsToCandidacy(idOpportunity);
    }
}
