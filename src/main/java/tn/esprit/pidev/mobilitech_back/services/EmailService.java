package tn.esprit.pidev.mobilitech_back.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import tn.esprit.pidev.mobilitech_back.entities.Event;
import tn.esprit.pidev.mobilitech_back.entities.User;


@Service
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    public void sendEmail(Event event, User user) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(user.getEmail());
        helper.setSubject("Appointment Invitation");

        Context context = new Context();
        context.setVariable("event", event);
        context.setVariable("user", user);
        System.out.println(event.getTypeEvent());
        String html = templateEngine.process("mail_template", context);

        helper.setText(html, true);

        javaMailSender.send(message);
    }
}