package tn.esprit.pidev.mobilitechdraft.entities;

import com.google.api.client.util.DateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    @NotBlank(message = "champ title non fourni")
    private String eventTitle;

    @NotBlank(message = "champ description non fourni")
    private String eventDescription;

    @Temporal(TemporalType.DATE)
    private Date eventDate;

    private DateTime eventStartTime;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    private DateTime eventEndTime;

    private String eventLinkHangout;

    private String eventLocation;

    @JsonIgnore
    @ManyToMany
    private List<User> users;
}
