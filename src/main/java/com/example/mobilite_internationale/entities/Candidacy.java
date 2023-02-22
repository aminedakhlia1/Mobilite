package com.example.mobilite_internationale.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Candidacy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCandidacy;

    private double score;

    @Enumerated(EnumType.STRING)
    private Speciality speciality;

    @Enumerated(EnumType.STRING)
    private Status status;

    //Associations
    @JsonIgnore
    @ManyToOne
    private Opportunity opportunity;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="candidacy")
    private List<File> fileList;
}
