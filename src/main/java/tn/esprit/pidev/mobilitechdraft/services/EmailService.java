package tn.esprit.pidev.mobilitechdraft.services;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import tn.esprit.pidev.mobilitechdraft.entities.Event;
import tn.esprit.pidev.mobilitechdraft.entities.User;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    public void sendEmail(Event event, User user,String x) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(user.getEmail());
        helper.setSubject("Appointment Invitation");

        Context context = new Context();
        context.setVariable("event", event);
        context.setVariable("user", user);
        context.setVariable("x", x);
        System.out.println(event.getEventType());
        String html = templateEngine.process("mail_template", context);

        helper.setText(html, true);

        javaMailSender.send(message);
    }
}