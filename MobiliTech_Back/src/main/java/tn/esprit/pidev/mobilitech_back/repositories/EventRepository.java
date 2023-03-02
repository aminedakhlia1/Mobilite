package tn.esprit.pidev.mobilitech_back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.mobilitech_back.entities.Event;
import tn.esprit.pidev.mobilitech_back.entities.User;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> getEventsByUsersOrderByDate(User user);

}
