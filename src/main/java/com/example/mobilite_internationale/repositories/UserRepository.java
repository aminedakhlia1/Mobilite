package com.example.mobilite_internationale.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mobilite_internationale.entities.Accommodation;
import com.example.mobilite_internationale.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

}
