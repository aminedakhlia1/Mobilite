package tn.esprit.pidev.mobilitechdraft.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteFormation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favoriteFormationId;

    @ManyToOne
    private User user;

    //@JsonIgnoreProperties("reservationFormations")
    @ManyToOne
    private Formation formation;

}
