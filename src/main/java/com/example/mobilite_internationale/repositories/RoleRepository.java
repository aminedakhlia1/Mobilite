package com.example.mobilite_internationale.repositories;

import com.example.mobilite_internationale.entities.ERole;
import com.example.mobilite_internationale.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
