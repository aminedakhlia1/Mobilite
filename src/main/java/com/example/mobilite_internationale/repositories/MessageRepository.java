package com.example.mobilite_internationale.repositories;

import com.example.mobilite_internationale.entities.File;
import com.example.mobilite_internationale.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Integer> {
}
