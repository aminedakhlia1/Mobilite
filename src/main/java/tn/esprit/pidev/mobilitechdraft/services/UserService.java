package tn.esprit.pidev.mobilitechdraft.services;

import tn.esprit.pidev.mobilitechdraft.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);

    Optional<User> getUserById(Integer idUser);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUser(Integer idUser);
}
