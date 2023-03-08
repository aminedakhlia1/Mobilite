package tn.esprit.pidev.mobilitechdraft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.mobilitechdraft.entities.User;
import tn.esprit.pidev.mobilitechdraft.services.UserServiceImplementation;

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

    @GetMapping("/get/{idUser}")
    public Optional<User> getUserById(@PathVariable Integer idUser) {
        return userServiceImplementation.getUserById(idUser);
    }

    /*@PutMapping("/update/{idUser}")
    public User updateUser(@PathVariable("idUser") Integer idUser, @RequestBody User user) {
        user.getIdUser(idUser);
        return userServiceImplementation.updateUser(user);

    }*/

    @DeleteMapping("/delete/{idUser}")
    public void deleteUser(@PathVariable("idUser") Integer idUser) {
        userServiceImplementation.deleteUser(idUser);
    }

}
