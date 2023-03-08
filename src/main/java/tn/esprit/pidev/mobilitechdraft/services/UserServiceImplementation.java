package tn.esprit.pidev.mobilitechdraft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.mobilitechdraft.entities.User;
import tn.esprit.pidev.mobilitechdraft.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Integer idUser) {
        return userRepository.findByIdUser(idUser);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer idUser) {
        userRepository.deleteById(Long.valueOf(idUser));

    }
}
