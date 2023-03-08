package tn.esprit.pidev.mobilitechdraft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.mobilitechdraft.entities.Event;
import tn.esprit.pidev.mobilitechdraft.entities.User;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> getEventsByUsersOrderByEventDate(User user);

}
