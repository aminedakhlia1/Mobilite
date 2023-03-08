package tn.esprit.pidev.mobilitechdraft.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    private ReservationFormationStatus reservationFormationStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;


    @ManyToOne(cascade = CascadeType.ALL)
    private Formation formation;

}
