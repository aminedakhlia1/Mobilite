package tn.esprit.pidev.mobilitech_back.services;

import tn.esprit.pidev.mobilitech_back.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);

    Optional<User> getUserById(Long userId);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUser(Long userId);
}
