package com.example.mobilite_internationale.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.net.SocketFlow;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Candidacy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCandidacy;

    //@NotNull
    //@DecimalMin("0.0")
    //@DecimalMax("20.0")
    private  float average_1year;

    //@NotNull
    //@DecimalMin("0.0")
    //@DecimalMax("20.0")
    private  float average_2year;

    //@NotNull
    //@DecimalMin("0.0")
    //@DecimalMax("20.0")
    private float average_3year;

    private float score;

    //@NotNull
    @Enumerated(EnumType.STRING)
    private Speciality speciality;

    @Enumerated(EnumType.STRING)
    private Status status;

    //Associations
    @JsonIgnore
    @ManyToOne
    private Opportunity opportunity;

    @JsonIgnore
    @OneToOne(mappedBy = "candidacy", cascade = CascadeType.ALL)
    private File file;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    User user;

}
