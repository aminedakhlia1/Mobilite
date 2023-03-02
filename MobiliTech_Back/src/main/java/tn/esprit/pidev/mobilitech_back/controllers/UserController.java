package tn.esprit.pidev.mobilitech_back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.mobilitech_back.entities.User;
import tn.esprit.pidev.mobilitech_back.services.UserServiceImplementation;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServiceImplementation userServiceImplementation;


    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userServiceImplementation.createUser(user);
    }

    @GetMapping("/getAll")
    public List<User> getAllUsers() {

        return userServiceImplementation.getAllUsers();
    }

    @GetMapping("/get/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userServiceImplementation.getUserById(id);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        user.setId(id);
        return userServiceImplementation.updateUser(user);

    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userServiceImplementation.deleteUser(id);
    }

}
