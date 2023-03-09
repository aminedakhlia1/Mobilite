package tn.esprit.pidev.mobilitechdraft.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    private Long formationId;

    @NotBlank(message = "formationSubjet: Required field")
    private String formationSubjet;

    private Date formationDate;

    private LocalTime formationStartTime;

    private LocalTime formationEndTime;

    @NotBlank(message = "formationFormer: Required fields")
    private String formationFormer;

    private boolean formationCertified;

    @JsonIgnore
    @OneToMany(mappedBy = "formation")
    private List<ReservationFormation> reservationFormations;
}
