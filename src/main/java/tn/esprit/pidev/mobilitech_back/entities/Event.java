package tn.esprit.pidev.mobilitech_back.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.api.client.util.DateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

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
    private Long id;
    private String title;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date date;
    private DateTime startTime;

    @Enumerated(EnumType.STRING)
    private TypeEvent typeEvent;
    private DateTime endTime;
    private String linkHangout;
    private String location;

    @JsonIgnore
    @ManyToMany
    private List<User> users;
}