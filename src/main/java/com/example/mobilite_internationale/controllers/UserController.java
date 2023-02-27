package com.example.mobilite_internationale.controllers;

import com.example.mobilite_internationale.entities.Message;
import com.example.mobilite_internationale.entities.Opportunity;
import com.example.mobilite_internationale.entities.User;
import com.example.mobilite_internationale.interfaces.MobiliteInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    MobiliteInterface mobiliteInterface;
    /*-------------- User --------------*/
    @PostMapping("/add-user")
    public User addUser(@RequestBody User uupp) {
        User user = mobiliteInterface.addUser(uupp);
        return user;
    }
    @GetMapping("/retrieve-all-users")
    public List<User> retrieveAllUsers() {
        List<User> users = mobiliteInterface.retrieveAllUsers();
        return users;
    }
    @GetMapping("/retrieve-user/{id-user}")
    public User retrieveUsers(@PathVariable("id-user") Long iduser) {
        return mobiliteInterface.retrieveUser(iduser);
    }
    @PutMapping("/update-user")
    public User updateUser(@RequestBody User u) {
        User user = mobiliteInterface.updateUser(u);
        return user;
    }
    @DeleteMapping("/remove-user/{id-user}")
    public void removeUser(@PathVariable("id-user") Long idUser) {
        mobiliteInterface.removeUser(idUser);
    }
    /*-------------- Message --------------*/
    @PostMapping("/add-message")
    public Message addMessage(@RequestBody Message mess) {
        Message message = mobiliteInterface.addMessage(mess);
        return message;

    }
    @GetMapping("/retrieve-all-message")
    public List<Message> retrieveAllmessage() {
        List<Message> messages = mobiliteInterface.retrieveAllMessages();
        return messages;
    }
    @GetMapping("/retrieve-message/{id-message}")
    public Message retrieveMessage(@PathVariable("id-message") Integer idMessage) {
        return mobiliteInterface.retrieveMessage(idMessage);
    }
    @PutMapping("/update-message")
    public Message updateMessage(@RequestBody Message m) {
        Message message = mobiliteInterface.updateMessage(m);
        return message;
    }
    @DeleteMapping("/remove-message/{id-message}")
    public void removeMessage(@PathVariable("id-message") Integer idmessage) {
        mobiliteInterface.removeMessage(idmessage);
    }


}
