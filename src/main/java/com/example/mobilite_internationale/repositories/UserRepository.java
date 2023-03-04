package com.example.mobilite_internationale.repositories;

import com.example.mobilite_internationale.entities.Opportunity;
import com.example.mobilite_internationale.entities.Role;
import com.example.mobilite_internationale.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    List<User> findByRole(Role role);
}
