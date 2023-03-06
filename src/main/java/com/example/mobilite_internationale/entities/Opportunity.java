package com.example.mobilite_internationale.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Opportunity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOpportunity;

    private String nameOpportunity;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private Speciality speciality;

    private Integer capacity;

    @Enumerated(EnumType.STRING)
    private TypeOpportunity typeOpportunity;

    @Enumerated(EnumType.STRING)
    private TypeSelection typeSelection;

    private float noteEliminatoire;

    private String formula;

    //Associations
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="opportunity")
    private List<Candidacy> candidacyList;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    User user;
}

