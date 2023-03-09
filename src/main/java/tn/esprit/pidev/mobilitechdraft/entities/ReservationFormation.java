package tn.esprit.pidev.mobilitechdraft.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationFormation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationFormationId;

    @Enumerated(EnumType.STRING)
    private ReservationFormationStatus reservationFormationStatus;

    @ManyToOne
    private User user;

    @JsonIgnoreProperties("reservationFormations")
    @ManyToOne
    private Formation formation;

}
