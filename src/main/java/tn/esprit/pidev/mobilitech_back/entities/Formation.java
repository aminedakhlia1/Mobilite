package tn.esprit.pidev.mobilitech_back.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Formation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sujet;
    private Date dateFormation;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private String formateur;
    private boolean certifiee;

    @OneToMany(mappedBy = "formation", cascade = CascadeType.ALL)
    private List<Reservation> reservations;
}

